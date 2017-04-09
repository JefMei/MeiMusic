package com.meimei.meimusic.module.main.songs;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.SongList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class SongsFragment extends BaseFragment implements ISongsView{

    @BindView(R.id.recyc_songs)RecyclerView mSongsRecycView;

    private final int pageSize = 20;   //默认每页20条信息

    private int pageNo = 0;   //记录当前请求到了第几页

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
    }

    private void initData() {
        mPresenter.loadSongs(pageSize,pageNo);
    }

    private void initRecyclerView() {

        mSongAdapter = new SongsAdapter(getActivity());

        mSongsRecycView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mSongsRecycView.setAdapter(mSongAdapter);
    }

    /**
     * 请求歌单数据成功
     */
    @Override
    public void requestSongsSucces(List<SongList.Song> songList) {
        if (mSongAdapter.size() == 0){
            mSongAdapter.loadData(songList);

        }else {
            mSongAdapter.addDataList(songList);
        }
    }

    /**
     * 请求歌单数据失败
     */
    @Override
    public void requestSongsError(String error) {
        if (true){}
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
