package com.meimei.meimusic.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.meimei.meimusic.base.adapter.BaseVpFragmentAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class MainPagerAdapter extends BaseVpFragmentAdapter {

    private final String[] TITLES = {"个性推荐","歌单","排行榜","主播电台"};
    FragmentManager mFragmentManager;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public List<String> getTitle() {
        return Arrays.asList(TITLES);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commit();

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragmentManager.beginTransaction().hide((Fragment) object).commit();
    }
}
