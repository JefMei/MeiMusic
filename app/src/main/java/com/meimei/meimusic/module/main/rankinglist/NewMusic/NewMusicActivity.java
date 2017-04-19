package com.meimei.meimusic.module.main.rankinglist.newmusic;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.utils.DensityUtil;
import com.meimei.meimusic.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicActivity extends BaseActivity implements INewMusicView{

    @BindView(R.id.toolbar_ranking_official)
    Toolbar mToolbar;
    @BindView(R.id.image_back_toolbar_ranking_official)
    ImageView mBack;
    @BindView(R.id.tv_title_toolbar_ranking_official)
    TextView mTitle;
    @BindView(R.id.relativelayout_ranking_official_header)
    RelativeLayout mHeaderLayout;
    @BindView(R.id.framelayout_ranking_official)
    FrameLayout mFrameLayout;
    @BindView(R.id.linearlayout_loading)
    LinearLayout mLoading;
    @BindView(R.id.tv_neterror_ranking_official)
    TextView mTvNetError;
    @BindView(R.id.image_song_loading)
    ImageView mImageLoading;

    private final String TAG = "NewMusicActivity";
    private final int requestNum = 100;

    private int mStatusHeight;

    private RecyclerView mRecycNewMusic;
    private NewMusicAdapter mAdapter;

    private NewMusicPresenter mPresenter;

    @Override
    protected void initFragment() {

    }

    @Override
    protected void initView() {
        initOther();
        initToolbar();
        initRecyclerView();
        initData();
    }

    private void initOther() {
        mStatusHeight = DensityUtil.getStatusHeight(this);
        mPresenter = new NewMusicPresenter(this);

        mHeaderLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.relativelayout_bg_official_header_new_music)));

        mPresenter.requestNewMusicForNet(requestNum);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar_bg_official_new_music)));
        mToolbar.setPadding(0,mStatusHeight,0,0);

    }

    private void initRecyclerView() {
        mRecycNewMusic = new RecyclerView(this);
        mAdapter = new NewMusicAdapter();
        mRecycNewMusic.setNestedScrollingEnabled(false);
        mRecycNewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecycNewMusic.setAdapter(mAdapter);
    }

    private void initData() {

    }

    @Override
    public void showNewMusicList(List<RankingList.songList> songlist) {

        mFrameLayout.addView(mRecycNewMusic);
        boolean showDataSuccess = mAdapter.loadData(songlist);

        LogUtil.i(TAG,"showDataSuccess" + showDataSuccess);
    }

    public void showLoading(){
        ((AnimationDrawable)mImageLoading.getBackground()).start();
        mLoading.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        mLoading.setVisibility(View.GONE);
        ((AnimationDrawable)mImageLoading.getBackground()).stop();
    }

    public void showNetError(){
        mTvNetError.setVisibility(View.VISIBLE);
    }

    public void hideNetError(){
        mTvNetError.setVisibility(View.GONE);
    }



    @OnClick(R.id.image_back_toolbar_ranking_official)
    void onBack1(){
        finish();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ranking_official_new;
    }
}
