package com.meimei.meimusic.module.main;

import android.support.v4.app.FragmentManager;

import com.meimei.meimusic.base.adapter.BaseVpFragmentAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class MainPagerAdapter extends BaseVpFragmentAdapter {

    private final String[] TITLES = {"个性推荐","歌单","排行榜"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public List<String> getTitle() {
        return Arrays.asList(TITLES);
    }

}
