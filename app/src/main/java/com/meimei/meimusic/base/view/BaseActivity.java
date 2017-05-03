package com.meimei.meimusic.base.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meimei.meimusic.service.IMusicBinder;
import com.meimei.meimusic.service.MusicService;

import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private IMusicBinder musicBinder;
    private MusicServiceConnection musicConnetion;

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
        unbindService(musicConnetion);
    }

    private void initService() {
        Intent intent = new Intent(this, MusicService.class);
        musicConnetion = new MusicServiceConnection();
        bindService(intent,musicConnetion,BIND_AUTO_CREATE);
    }

    protected IMusicBinder getMusicBinder(){
        return musicBinder;
    }

    private class MusicServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (IMusicBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
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
