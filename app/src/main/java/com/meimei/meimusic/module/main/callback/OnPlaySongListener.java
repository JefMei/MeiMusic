package com.meimei.meimusic.module.main.callback;

/**
 * Created by 梅梅 on 2017/4/23.
 */
public interface OnPlaySongListener {

    void play(String songUrl,int position);

    void playError(String errorInfo);
}
