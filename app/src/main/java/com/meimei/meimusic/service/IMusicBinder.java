package com.meimei.meimusic.service;

/**
 * Created by 梅梅 on 2017/4/17.
 */
public interface IMusicBinder{

    void playMusic(String url);

    void start();

    void pause();

    void playOrPause();

    void stop();

    boolean isPlaying();

    int getCurrentPosition();

    int getDuration();
}
