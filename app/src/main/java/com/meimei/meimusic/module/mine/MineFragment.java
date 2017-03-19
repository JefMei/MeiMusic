package com.meimei.meimusic.module.mine;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class MineFragment extends BaseFragment{
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    public static MineFragment newInstance(){
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }
}
