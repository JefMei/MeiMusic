package com.meimei.meimusic.module;

import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class MainActivity extends BaseActivity{

    private NavigationView mNavView;

    @Override
    protected void initFragment() {

    }

    @Override
    protected void initView() {
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setItemIconTintList(null);
        ColorStateList colorStateList = getResources().getColorStateList(R.color.nav_text);
        mNavView.setItemTextColor(colorStateList);
        mNavView.setItemTextAppearance(R.style.nav_text);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

}
