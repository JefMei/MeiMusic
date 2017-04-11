package com.meimei.meimusic.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public abstract class BaseVpFragmentAdapter<T> extends FragmentPagerAdapter{

    private DataController<Fragment> mFragments = new DataController<>();

    public BaseVpFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.getData(position);
    }

    @Override
    public int getCount() {
        return mFragments.getDataSize();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle().get(position);
    }

    public boolean setFragments(List<Fragment> fragments){
        mFragments.updateData(fragments);
        return true;
    }

    public abstract List<String> getTitle();

}
