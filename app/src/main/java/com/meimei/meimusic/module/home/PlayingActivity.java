package com.meimei.meimusic.module.home;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/5/1.
 */
public class PlayingActivity extends BaseActivity{

    @BindView(R.id.toolbar_playing)
    Toolbar mToolbar;

    private ActionBar mActionBar;

    @Override
    protected void initFragment() {

    }

    @Override
    protected void initView() {
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.mipmap.ic_actionbar_back);
        mToolbar.setNavigationOnClickListener(onBack);

    }

    private View.OnClickListener onBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_playing;
    }


}
