package com.meimei.meimusic.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView();
        if (savedInstanceState == null){
            initFragment();
        }
    }

    protected abstract void initFragment();

    protected abstract void initView();

    protected abstract int getLayoutRes();
}
