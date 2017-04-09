package com.meimei.meimusic.module.main.songs;

import com.meimei.meimusic.base.view.BaseView;
import com.meimei.meimusic.entity.SongList;

import java.util.List;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public interface ISongsView extends BaseView{

    void requestSongsSucces(List<SongList.Song> songList);

    void requestSongsError(String error);
}
