package com.meimei.meimusic.module.main.rankinglist;

import com.meimei.meimusic.base.model.BaseModel;
import com.meimei.meimusic.module.main.callback.OnRankingListener;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public interface IRankingModel extends BaseModel{

    void requestRankingListForNet(int type, int offset, int size, OnRankingListener listener);
}
