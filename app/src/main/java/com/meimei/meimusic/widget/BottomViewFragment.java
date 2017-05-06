package com.meimei.meimusic.widget;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.module.home.PlayingActivity;
import com.meimei.meimusic.utils.IntentUtil;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.MusicUtil;
import com.meimei.meimusic.utils.NetWorkUtils;
import com.meimei.meimusic.utils.SharedPrefrencesManager;

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

        SharedPrefrencesManager.getInstance().setString(SharedPrefrencesManager.SONGNAME, songInfo.title);
        SharedPrefrencesManager.getInstance().setString(SharedPrefrencesManager.SINGER, songInfo.author);
        SharedPrefrencesManager.getInstance().setString(SharedPrefrencesManager.PICURL, songInfo.pic_small);
        SharedPrefrencesManager.getInstance().setString(SharedPrefrencesManager.SONGURL, songUrl);

        updateBottomView();
    }

    public void updateBottomView() {

        String pic = SharedPrefrencesManager.getInstance().getString(SharedPrefrencesManager.PICURL, "");

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

        mBtnSongName.setText(SharedPrefrencesManager.getInstance().getString(SharedPrefrencesManager.SONGNAME, ""));
        mBtnSongAuthor.setText(SharedPrefrencesManager.getInstance().getString(SharedPrefrencesManager.SINGER, ""));

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
            int playedTime = SharedPrefrencesManager.getInstance().getInt(SharedPrefrencesManager.PLAYEDPOSITION, 0);
            int songDuration = SharedPrefrencesManager.getInstance().getInt(SharedPrefrencesManager.SONGDURATION, 0);

            mProgressBar.setProgress(songDuration == 0 ? 0 : (mProgressBar.getMax() * playedTime / songDuration));

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
        IntentUtil.startActivityForResult(getActivity(), PlayingActivity.class);
    }

    @OnClick(R.id.image_bottom_playlist)
    void doPlayList() {

    }

    @OnClick(R.id.image_bottom_play)
    void doPlay() {

        String songUrl = SharedPrefrencesManager.getInstance().getString(SharedPrefrencesManager.SONGURL, "");

        /**
         * 如果是刚打开App进来，点击播放按钮，就会播放上次退出时播放的歌曲，并且跳到上次播放的位置
         */
        if (MusicUtil.isFirstPlay() && !songUrl.equals("")) {

            MusicUtil.seekTo(songUrl, SharedPrefrencesManager.getInstance().getInt(SharedPrefrencesManager.PLAYEDPOSITION, 0));
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
