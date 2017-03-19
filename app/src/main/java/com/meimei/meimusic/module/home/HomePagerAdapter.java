package com.meimei.meimusic.module.home;

import android.support.v4.app.FragmentManager;

import com.meimei.meimusic.base.adapter.BaseVpFragmentAdapter;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class HomePagerAdapter extends BaseVpFragmentAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public List<String> getTitle() {
        return null;
    }

}
