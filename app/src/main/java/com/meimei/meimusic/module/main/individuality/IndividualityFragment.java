package com.meimei.meimusic.module.main.individuality;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.widget.CarouselView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class IndividualityFragment extends BaseFragment implements IIndividualityView {

    @BindView(R.id.recyc_recommend_song)
    RecyclerView mRecyclerView;

    private final Integer[] imageRes = {R.mipmap.ic_vp_first,R.mipmap.ic_vp_second,R.mipmap.ic_vp_third,
            R.mipmap.ic_vp_fourth,R.mipmap.ic_vp_five,R.mipmap.ic_vp_six,R.mipmap.ic_vp_seven};

    private List<View> mViews = new ArrayList<>();

    private List<Individuality.Recommend_Item> recomSongList = new ArrayList<>();

    private IndividualityPresenter mPresenter;

    private RecomSongAdapter mAdapter;

    private CarouselView mVpView;


    @Override
    protected void initView() {
        mPresenter = new IndividualityPresenter(this);
        mVpView = (CarouselView) getView().findViewById(R.id.carouseview_individuality);
        mVpView.setData(Arrays.asList(imageRes));

        mPresenter.loadRecomSong();
        mAdapter = new RecomSongAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_individuality;
    }

    public static IndividualityFragment newInstance(){
        IndividualityFragment individualityFragment = new IndividualityFragment();
        return individualityFragment;
    }

    @Override
    public void requestRecomSongSuccess(List<Individuality.Recommend_Item> list) {
        recomSongList.addAll(list);
        mAdapter.loadData(recomSongList);

    }

    @Override
    public void requestRecomSongError(String errorInfo) {

    }
}
