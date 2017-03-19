package com.meimei.meimusic.module.main.songs;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class SongsFragment extends BaseFragment{
    @Override
    protected void initView() {

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
