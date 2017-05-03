package com.meimei.meimusic.base.view;

import android.os.Build;
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
        setStatusBarColor();
        setContentView(getLayoutRes());
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_bg));*/
        }
    }


}
