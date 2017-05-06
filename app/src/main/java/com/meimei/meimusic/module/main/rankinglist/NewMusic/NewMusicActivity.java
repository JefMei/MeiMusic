package com.meimei.meimusic.module.main.rankinglist.newmusic;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.module.main.callback.OnPlaySongListener;
import com.meimei.meimusic.utils.DensityUtil;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.MusicUtil;
import com.meimei.meimusic.utils.ToastUtil;
import com.meimei.meimusic.widget.BottomViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicActivity extends BaseActivity implements INewMusicView{

    @BindView(R.id.toolbar_ranking_official)
    Toolbar mToolbar;
    @BindView(R.id.tv_title_toolbar_ranking_official)
    TextView mTitle;
    @BindView(R.id.relativelayout_ranking_official_header)
    RelativeLayout mHeaderLayoutText;
    @BindView(R.id.framelayout_ranking_official_header)
    FrameLayout mHeaderLayout;
    @BindView(R.id.recyc_ranking_official)
    RecyclerView mRecycNewMusic;
    @BindView(R.id.linearlayout_loading)
    LinearLayout mLoading;
    @BindView(R.id.tv_neterror_loading)
    TextView mTvNetError;
    @BindView(R.id.image_song_loading)
    ImageView mImageLoading;

    private final String TAG = "NewMusicActivity";
    private final int requestNum = 100;

    private List<RankingList.songList> mSongList = new ArrayList<>();

    private int mStatusHeight;
    private ActionBar mActionBar;

    private NewMusicPresenter mPresenter;

    private NewMusicAdapter mAdapter;
    private NestedScrollView mScrollView;

    private BottomViewFragment mBottomView;

    @Override
    protected void onStart() {
        super.onStart();
        mBottomView.updateBottomView();
    }

    @Override
    protected void initFragment() {
        mBottomView = BottomViewFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout_ranking_bottom,mBottomView)
                .commit();
    }

    @Override
    protected void initView() {

        initOther();
        initToolbar();
        initRecyclerView();
        initData();
    }

    private void initOther() {
        mScrollView = (NestedScrollView) findViewById(R.id.scrollview_ranking_official);

        mStatusHeight = DensityUtil.getStatusHeight(this);
        mPresenter = new NewMusicPresenter(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mHeaderLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.relativelayout_bg_official_header_new_music)));
        }

        mScrollView.setOnScrollChangeListener(onScrollChangeListener);
    }

    private void initToolbar() {

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar_bg_transparent_official_new_music)));
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.mipmap.ic_actionbar_back);

        mToolbar.setNavigationOnClickListener(onBack);

//        mToolbar.setPadding(0,mStatusHeight,0,0);
    }

    private void initRecyclerView() {

        mAdapter = new NewMusicAdapter(onPlaySongListener);
        mRecycNewMusic.setNestedScrollingEnabled(false);
        mRecycNewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecycNewMusic.setAdapter(mAdapter);

    }

    private void initData() {
        mPresenter.requestNewMusicForNet(requestNum);

    }

    @Override
    public void showNewMusicList(List<RankingList.songList> songlist) {

        mSongList.addAll(songlist);

        mAdapter.addData(null);
        boolean showDataSuccess = mAdapter.addDataList(songlist);

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

    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_OK);
            onBackPressed();
        }
    };

    private NestedScrollView.OnScrollChangeListener onScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            int flag = DensityUtil.dp2px(MyApplication.getContext(),70);

            if (scrollY > flag && scrollY < (mHeaderLayout.getHeight() - mToolbar.getHeight())){
                mTitle.setText("云音乐新歌榜");
            }

            if (scrollY < flag){
                mTitle.setText("");
            }

            float TransParentScale = (float) scrollY / (mHeaderLayout.getHeight() - mToolbar.getHeight());  //透明度比例

            mToolbar.getBackground().setAlpha(Math.min((int) (TransParentScale * 255),255));
            mHeaderLayoutText.setAlpha(1f - TransParentScale);
        }
    };

    private OnPlaySongListener onPlaySongListener = new OnPlaySongListener() {
        @Override
        public void play(final String songUrl, final int position) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MusicUtil.playMusic(songUrl);
                    mBottomView.updateBottomView(mSongList.get(position),songUrl);
                }
            });

        }

        @Override
        public void playError(String errorInfo) {
            ToastUtil.show(errorInfo);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case RESULT_OK:
                mBottomView.updateBottomView();
                break;
            default:
                break;
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ranking_official_new;
    }

}
