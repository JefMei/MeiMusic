package com.meimei.meimusic.module.main.callback;

import com.meimei.meimusic.entity.SongList;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public interface OnSongsListener {

    void succes(SongList songList);
    void error(Throwable throwable);

}
