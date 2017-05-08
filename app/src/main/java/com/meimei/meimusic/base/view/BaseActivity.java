package com.meimei.meimusic.base.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransition();
        setContentView(getLayoutRes());
        setStatusBarColor();

        ButterKnife.bind(this);

        //如果是异常退出情况后启动的Activity，则fragment已经有保存，不需要在进行初始化操作
        if (savedInstanceState == null){
            initFragment();
        }

        initService();
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MusicUtil.unbindService(this);
    }

    protected void initService() {
//        MusicUtil.bindService(this);
    }

    protected abstract void initFragment();

    protected abstract void initView();

    protected abstract int getLayoutRes();

    protected void setStatusBarColor(){
        View decorView = getWindow().getDecorView();
        //两个flag必须一起用，表示会让应用主体内容占用状态栏的空间
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    protected void setTransition(){

    }

}
