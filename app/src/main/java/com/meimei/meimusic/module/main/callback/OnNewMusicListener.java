package com.meimei.meimusic.module.main.callback;

import com.meimei.meimusic.entity.RankingList;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public interface OnNewMusicListener {

    void succuss(RankingList rankingList);
    void error(Throwable throwable);
}
