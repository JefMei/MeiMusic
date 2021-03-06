package com.meimei.meimusic.service;

/**
 * Created by 梅梅 on 2017/4/17.
 */
public interface IMusicBinder{

    void playMusic(String url);

    void play();

    void pause();

    void playOrPause();

    void stop();

    void seekTo(String url,int position);

    void seekTo(int position);

    boolean isPlaying();

    int getCurrentPosition();

    int getDuration();
}
