package com.meimei.meimusic.module.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.module.main.individuality.IndividualityFragment;
import com.meimei.meimusic.module.main.rankinglist.RankingFragment;
import com.meimei.meimusic.module.main.songs.SongsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class MainFragment extends BaseFragment{

    @BindView(R.id.tab_fragment_main)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_framgnet_main)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void initView() {
        initFragment();
        initViewPager();
    }

    private void initFragment(){
        IndividualityFragment individualityFragment = IndividualityFragment.newInstance();
        SongsFragment songsFragment = SongsFragment.newInstance();
        RankingFragment rankingFragment = RankingFragment.newInstance();
        mFragments.add(individualityFragment);
        mFragments.add(songsFragment);
        mFragments.add(rankingFragment);
    }

    private void initViewPager(){
        MainPagerAdapter mAdapter = new MainPagerAdapter(getFragmentManager());
        mAdapter.setFragments(mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }
}
