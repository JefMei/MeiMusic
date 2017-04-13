package com.meimei.meimusic.module.main.rankinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class RankingFragment extends BaseFragment{

    @BindView(R.id.linearlayout_views)
    LinearLayout mViewsLayout;

    private View mOfficialView;


    @Override
    protected void initView() {
        initRecyclerView();
        addView();
    }

    private void initRecyclerView() {
        mOfficialView = LayoutInflater.from(getActivity()).inflate(R.layout.view_official_ranking,null,false);
    }

    private void addView() {
        mViewsLayout.addView(mOfficialView);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_ranking;
    }

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment = new RankingFragment();
        return rankingFragment;
    }
}
