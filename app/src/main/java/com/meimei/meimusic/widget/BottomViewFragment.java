package com.meimei.meimusic.widget;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.module.home.playing.PlayingActivity;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.MusicUtil;
import com.meimei.meimusic.utils.NetWorkUtils;
import com.meimei.meimusic.utils.PrefrencesManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/5/5.
 */
public class BottomViewFragment extends BaseFragment {

    @BindView(R.id.image_main_song_pic)
    protected ImageView mBtnSongPic;
    @BindView(R.id.tv_main_song_name)
    protected TextView mBtnSongName;
    @BindView(R.id.tv_main_song_author)
    protected TextView mBtnSongAuthor;
    @BindView(R.id.relative_bottom)
    protected RelativeLayout mBtnView;
    //按钮
    @BindView(R.id.image_bottom_playlist)
    protected ImageView mBtnPlayList;
    @BindView(R.id.image_bottom_play)
    protected ImageView mBtnPlay;
    @BindView(R.id.image_bottom_next)
    protected ImageView mBtnNext;
    @BindView(R.id.progressbar_bottom)
    protected ProgressBar mProgressBar;

    private Disposable disposable;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.view_bottom;
    }

    public void updateBottomView(RankingList.songList songInfo, String songUrl) {

        PrefrencesManager.getInstance().setString(PrefrencesManager.SONGNAME, songInfo.title);
        PrefrencesManager.getInstance().setString(PrefrencesManager.SINGER, songInfo.author);
        PrefrencesManager.getInstance().setString(PrefrencesManager.PICURL, songInfo.pic_small);
        PrefrencesManager.getInstance().setString(PrefrencesManager.BIGPICURL,songInfo.pic_big);
        PrefrencesManager.getInstance().setString(PrefrencesManager.SONGURL, songUrl);

        updateBottomView();
    }

    public void updateBottomView() {

        String pic = PrefrencesManager.getInstance().getString(PrefrencesManager.PICURL, "");

        if (!pic.equals("") && NetWorkUtils.isNetworkConnected()) {
            Glide.with(getActivity())
                    .load(pic)
                    .into(mBtnSongPic);
        }
        if (MusicUtil.isPlaying()) {
            mBtnPlay.setImageResource(R.mipmap.ic_pause);
        } else {
            mBtnPlay.setImageResource(R.mipmap.ic_play);
        }

        MusicUtil.SongInfo songInfo = new MusicUtil.SongInfo();
        songInfo.songName = PrefrencesManager.getInstance().getString(PrefrencesManager.SONGNAME, "");
        songInfo.singer =  PrefrencesManager.getInstance().getString(PrefrencesManager.SINGER, "");
        MusicUtil.putSongInfo(songInfo);

        mBtnSongName.setText(songInfo.songName);
        mBtnSongAuthor.setText(songInfo.singer);

        updateSeekbar();

    }

    public void updateSeekbar() {

        if (disposable == null){
            disposable =  Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            setSeekbar();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            setSeekbarFailure();
                        }
                    });
        }

        if (MusicUtil.isFirstPlay()) {
            int playedTime = PrefrencesManager.getInstance().getInt(PrefrencesManager.PLAYEDPOSITION, 0);
            int songDuration = PrefrencesManager.getInstance().getInt(PrefrencesManager.SONGDURATION, 0);

            mProgressBar.setProgress(songDuration == 0 ? 0 : (mProgressBar.getMax() * playedTime / songDuration));
            LogUtil.i("tag","first");
            LogUtil.i("tag","playedTime" + playedTime);
            LogUtil.i("tag","songDuration" + songDuration);
        }

    }

    public void setSeekbar() {

        if (MusicUtil.isPlaying() && !mProgressBar.isPressed()) {
            int position = MusicUtil.getCurrentPosition();
            int duration = MusicUtil.getDuration();

            if (duration > 0) {
                mProgressBar.setProgress(mProgressBar.getMax() * position / duration);
                LogUtil.i(getClass() + "", "" + mProgressBar.getMax() * position / duration);
            }
        }
    }

    public void setSeekbarFailure() {

    }

    public void clearLastSeekbar() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @OnClick(R.id.relative_bottom)
    void doBottomView() {
        /*IntentUtil.startActivityWithTransition(getActivity(),
                PlayingActivity.class, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());*/

        Intent intent = new Intent(getActivity(), PlayingActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.playingactivity_in,0);
    }

    @OnClick(R.id.image_bottom_playlist)
    void doPlayList() {

    }

    @OnClick(R.id.image_bottom_play)
    void doPlay() {

        String songUrl = PrefrencesManager.getInstance().getString(PrefrencesManager.SONGURL, "");

        if (songUrl.equals("")){
            return;
        }

        /**
         * 如果是刚打开App进来，点击播放按钮，就会播放上次退出时播放的歌曲，并且跳到上次播放的位置
         */
        if (MusicUtil.isFirstPlay() && !songUrl.equals("")) {

            MusicUtil.SongInfo songInfo = new MusicUtil.SongInfo();
            songInfo.singer = PrefrencesManager.getInstance().getString(PrefrencesManager.SINGER,"");
            songInfo.songName = PrefrencesManager.getInstance().getString(PrefrencesManager.SONGNAME,"");

            MusicUtil.seekTo(songUrl,songInfo, PrefrencesManager.getInstance().getInt(PrefrencesManager.PLAYEDPOSITION, 0));
            Glide.with(getActivity())
                    .load(R.mipmap.ic_pause)
                    .into(mBtnPlay);

        } else {

            if (MusicUtil.isPlaying() == true) {

                Glide.with(getActivity())
                        .load(R.mipmap.ic_play)
                        .into(mBtnPlay);
                MusicUtil.pause();

            } else {

                Glide.with(getActivity())
                        .load(R.mipmap.ic_pause)
                        .into(mBtnPlay);
                MusicUtil.play();

            }
        }

    }

    @OnClick(R.id.image_bottom_next)
    void doNext() {

    }

    public static BottomViewFragment newInstance() {
        return new BottomViewFragment();
    }

}
