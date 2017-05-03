package com.meimei.meimusic.module.home;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;
import com.meimei.meimusic.utils.IntentUtil;
import com.meimei.meimusic.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/5/1.
 */
public class PlayingActivity extends BaseActivity{

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

    private String mSinger;
    private String mSongName;

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

        Intent intent = getIntent();
        mSongName = intent.getStringExtra(IntentUtil.SONGNAME);
        mSinger = intent.getStringExtra(IntentUtil.SINGER);
        boolean isplaying = intent.getBooleanExtra(IntentUtil.ISPLAYING,false);

        if (isplaying){
            mBtnPlay.setImageResource(R.mipmap.ic_playing_pause);
        }

    }

    private void initToolbar() {

        mToolbar.setTitle(mSongName);
        mToolbar.setSubtitle(mSinger);
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
            onBackPressed();
        }
    };

    @OnClick(R.id.image_playing_play)
    void onPlay(){
        if (getMusicBinder().isPlaying()){
            LogUtil.i(TAG,"歌曲播放状态：" + getMusicBinder().isPlaying());
            mBtnPlay.setImageResource(R.mipmap.ic_playing_play);
            getMusicBinder().pause();
        }else {
            LogUtil.i(TAG,"歌曲播放状态：" + getMusicBinder().isPlaying());
            mBtnPlay.setImageResource(R.mipmap.ic_playing_pause);
            getMusicBinder().start();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_playing;
    }


}
