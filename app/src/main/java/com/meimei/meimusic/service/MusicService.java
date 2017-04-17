package com.meimei.meimusic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 梅梅 on 2017/4/17.
 */
public class MusicService extends Service{

    public MusicService() {
        Log.i("tag","MusicService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("tag","onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("tag","onBind");
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("tag","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("tag","onDestroy");
        super.onDestroy();
    }

    private class MusicBinder extends Binder implements IMusicBinder{

    }

}
