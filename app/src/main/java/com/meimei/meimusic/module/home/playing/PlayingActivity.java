package com.meimei.meimusic.module.home.playing;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.MusicUtil;
import com.meimei.meimusic.utils.PrefrencesManager;
import com.meimei.meimusic.utils.TimeUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/5/1.
 */
public class PlayingActivity extends BaseActivity implements IPlayingView{

    @BindView(R.id.image_playing_bg)
    ImageView mImageBg;
    @BindView(R.id.image_playing_needle)
    ImageView mImageNeedle;

    @BindView(R.id.toolbar_playing)
    Toolbar mToolbar;
    @BindView(R.id.image_playing_love)
    ImageView mBtnLove;
    @BindView(R.id.image_playing_download)
    ImageView mBtnDownLoad;
    @BindView(R.id.image_playing_comment)
    ImageView mBtnComment;
    @BindView(R.id.image_playing_more)
    ImageView mBtnMore;

    @BindView(R.id.tv_duration_played)
    TextView mTvPlayed;
    @BindView(R.id.tv_duration)
    TextView mTvDuration;
    @BindView(R.id.seekbar_playing_played)
    SeekBar mSeekbar;

    @BindView(R.id.image_playing_loop)
    ImageView mBtnLoop;
    @BindView(R.id.image_playing_previous)
    ImageView mBtnPrevious;
    @BindView(R.id.image_playing_play)
    ImageView mBtnPlay;
    @BindView(R.id.image_playing_next)
    ImageView mBtnNext;
    @BindView(R.id.image_playing_list)
    ImageView mBtnList;

    private final String TAG = "PlayingActivity";

    private ActionBar mActionBar;

    private PlayingPresenter mPresenter;

    private String songName;
    private String singer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG,"onDestroy");

    }

    @Override
    protected void initFragment() {

    }

    @Override
    protected void initView() {
        initOther();
        initToolbar();
    }

    private void initOther() {

        mPresenter = new PlayingPresenter(this);
        mSeekbar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        if (MusicUtil.isPlaying()){
            mBtnPlay.setImageResource(R.mipmap.ic_playing_pause);
        }

        mPresenter.updateSeekbar();

    }

    private void initToolbar() {

        mToolbar.setTitle(MusicUtil.getSongInfo().songName);
        mToolbar.setSubtitle(MusicUtil.getSongInfo().singer);
        mToolbar.setTitleMarginStart(0);

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.mipmap.ic_actionbar_back);
        mToolbar.setNavigationOnClickListener(onBack);

    }

    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            setResult(RESULT_OK);
            onBackPressed();
        }
    };

    @Override
    public void setPlayedTime(String playedTime) {
        mTvPlayed.setText(playedTime);
    }

    @Override
    public void setDurationTime(String durationTime) {
        mTvDuration.setText(durationTime);
    }

    @Override
    public void setFirstSeekbar() {

        int played;
        int duration;

        //有可能用户进来没播放音乐，这样MusicUtil.getDuration() 就会返回 0；
        if (MusicUtil.isPlaying()){
            played = MusicUtil.getCurrentPosition();
            duration = MusicUtil.getDuration();
        }else {
            played = PrefrencesManager.getInstance().getInt(PrefrencesManager.PLAYEDPOSITION,0);
            duration = PrefrencesManager.getInstance().getInt(PrefrencesManager.SONGDURATION,0);
        }

        mSeekbar.setProgress(duration == 0 ? 0 : mSeekbar.getMax() * played / duration);

    }

    @Override
    public void setSeekbar() {
        mSeekbar.setProgress(mSeekbar.getMax() * MusicUtil.getCurrentPosition() / MusicUtil.getDuration());
        mTvPlayed.setText(TimeUtil.formatTime(MusicUtil.getCurrentPosition()));
    }

    @Override
    public void updateSeekbarFailure() {

    }


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        /**
         * progress发生改变时就被回调
         * @param seekBar
         * @param progress
         * @param fromUser  true代表是用户在拉，false代表是代码调用改变
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            if (fromUser == true){
                setPlayedTime(TimeUtil.formatTime(progress * MusicUtil.getDuration() / 100 ));
            }

        }

        /**
         * 用手刚开始接触准备拉时 回调
         * @param seekBar
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            LogUtil.i(TAG,"onStartTrackingTouch");
        }

        /**
         * 手放开时 回调
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            LogUtil.i(TAG,"onStopTrackingTouch");
            MusicUtil.seekTo(seekBar.getProgress() * MusicUtil.getDuration() / 100);
        }
    };

    @OnClick(R.id.image_playing_play)
    void onPlay(){

        String songUrl = PrefrencesManager.getInstance().getString(PrefrencesManager.SONGURL, "");

        if (MusicUtil.isFirstPlay() && !songUrl.equals("")){
            MusicUtil.SongInfo songInfo = new MusicUtil.SongInfo();
            songInfo.singer = PrefrencesManager.getInstance().getString(PrefrencesManager.SINGER,"");
            songInfo.songName = PrefrencesManager.getInstance().getString(PrefrencesManager.SONGNAME,"");

            MusicUtil.seekTo(songUrl,songInfo, PrefrencesManager.getInstance().getInt(PrefrencesManager.PLAYEDPOSITION, 0));
            mBtnPlay.setImageResource(R.mipmap.ic_playing_pause);

            return;
        }

        if (MusicUtil.isPlaying()){
            LogUtil.i(TAG,"歌曲播放状态：" + MusicUtil.isPlaying());
            mBtnPlay.setImageResource(R.mipmap.ic_playing_play);
            MusicUtil.pause();
        }else {
            LogUtil.i(TAG,"歌曲播放状态：" + MusicUtil.isPlaying());
            mBtnPlay.setImageResource(R.mipmap.ic_playing_pause);
            MusicUtil.play();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_playing;
    }

}
