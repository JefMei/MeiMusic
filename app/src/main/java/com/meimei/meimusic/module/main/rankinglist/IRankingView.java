package com.meimei.meimusic.module.main.rankinglist;

import com.meimei.meimusic.base.view.BaseView;
import com.meimei.meimusic.entity.RankingList;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public interface IRankingView extends BaseView{

    void loadOfficialSuccess(RankingList songList);
    void loadOfficialError(String error);
}
