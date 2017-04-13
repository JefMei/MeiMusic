package com.meimei.meimusic.module.main.songs;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.SongList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class SongsFragment extends BaseFragment implements ISongsView {

    @BindView(R.id.recyc_songs)RecyclerView mSongsRecycView;
    @BindView(R.id.nestedscroll_songs)NestedScrollView mScrollView;

    @BindView(R.id.relative_song_options)RelativeLayout mOptions;
    @BindView(R.id.image_songs_options)ImageView mImageOptions;
    @BindView(R.id.tv_songs_options)TextView mTvOptions;

    private final int pageSize = 20;   //默认每页20条信息

    private int pageNo = 2;   //记录当前请求到了第几页

    private boolean loading = false;    //标记当前是否在下拉加载状态

    private SongsPresenter mPresenter;

    private SongsAdapter mSongAdapter;

    @Override
    protected void initView() {

        initOther();
        initData();
        initRecyclerView();
    }

    private void initOther() {
        mPresenter = new SongsPresenter(this);

        mSongsRecycView.setNestedScrollingEnabled(false);   //防止recyclerview和scrollview滑动冲突
        mScrollView.setOnScrollChangeListener(onScrollChangeListener);
        mOptions.setOnTouchListener(onOptions);
    }

    private void initData() {
        mPresenter.loadSongs(pageSize,pageNo);
    }

    private void initRecyclerView() {

        mSongAdapter = new SongsAdapter(getActivity());

        mSongsRecycView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mSongsRecycView.setAdapter(mSongAdapter);
    }

    private NestedScrollView.OnScrollChangeListener onScrollChangeListener =  new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if (!loading && (scrollY + 100 >= (v.getChildAt(0).getMeasuredHeight() - v.getHeight()))){
                mSongAdapter.getDataController().addData(null);
                mSongAdapter.notifyItemInserted(mSongAdapter.getItemCount() - 1);
                loading = true;
                mPresenter.loadMoreSongs(pageSize,++pageNo);
            }
        }
    };

    private View.OnTouchListener onOptions = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                return new GestureDetector(getActivity(),onGestureListener).onTouchEvent(event);
            }

            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE){
                ((GradientDrawable) mTvOptions.getBackground()).setColor(getResources().getColor(R.color.tv_color_options_title_normal));
                mImageOptions.setImageResource(R.drawable.ic_arrow_gray);
            }

            return false;

        }
    };

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            ((GradientDrawable) mTvOptions.getBackground()).setColor(getResources().getColor(R.color.tv_color_options_title_pressed));
            mImageOptions.setImageResource(R.drawable.ic_arrow_white);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    };

    /**
     * 请求歌单数据成功
     */
    @Override
    public void requestSongsSucces(List<SongList.Song> songList) {

        if (mSongAdapter.size() == 0){
            mSongAdapter.loadData(songList);

        }else {
            loading = false;
            mSongAdapter.remove(mSongAdapter.getItemCount() - 1);
            mSongAdapter.addDataList(songList);
        }

    }

    /**
     * 请求歌单数据失败
     */
    @Override
    public void requestSongsError(String error) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_songs;
    }

    public static SongsFragment newInstance(){
        SongsFragment songsFragment = new SongsFragment();
        return songsFragment;
    }



}
