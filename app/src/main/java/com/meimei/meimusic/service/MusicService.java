package com.meimei.meimusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.meimei.meimusic.utils.LogUtil;

import java.io.IOException;

/**
 * Created by 梅梅 on 2017/4/17.
 */
public class MusicService extends Service {

    private final String TAG = "MusicService";

    private MediaPlayer mMediaPlayer;

    public MusicService() {
        Log.i(TAG, "MusicService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        LogUtil.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i(TAG, "onBind");
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {

        mMediaPlayer.release(); //释放资源

        LogUtil.i(TAG, "onDestroy");
        super.onDestroy();
    }

    private class MusicBinder extends Binder implements IMusicBinder {

        @Override
        public void playMusic(String url) {

            mMediaPlayer.reset();//重置播放器，可以使之在Error状态恢复，一般可以在切换歌曲前，setDataSource之前重置下

            try {
                mMediaPlayer.setDataSource(url);
                mMediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

            start();

        }

        @Override
        public void start() {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
            }
        }

        @Override
        public void pause() {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
        }

        @Override
        public void playOrPause() {
            if (mMediaPlayer != null) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
            }
        }

        @Override
        public void stop() {
            mMediaPlayer.stop();
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
        }

        @Override
        public boolean isPlaying() {
            if (mMediaPlayer != null) {
                return mMediaPlayer.isPlaying();
            }
            return false;
        }

        @Override
        public int getDuration() {

            if (mMediaPlayer != null) {
                return mMediaPlayer.getDuration();
            }
            return 0;
        }

        @Override
        public int getCurrentPosition() {

            if (mMediaPlayer != null) {
                return mMediaPlayer.getCurrentPosition();
            }
            return 0;
        }


    }

}
