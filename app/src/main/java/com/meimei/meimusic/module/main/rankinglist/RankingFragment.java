package com.meimei.meimusic.module.main.rankinglist;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class RankingFragment extends BaseFragment{
    @Override
    protected void initView() {

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
