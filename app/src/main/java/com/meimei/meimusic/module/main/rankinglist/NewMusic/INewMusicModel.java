package com.meimei.meimusic.module.main.rankinglist.newmusic;

import com.meimei.meimusic.base.model.BaseModel;
import com.meimei.meimusic.module.main.callback.OnNewMusicListener;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public interface INewMusicModel extends BaseModel{

    void getNewMusicForNet(int size, OnNewMusicListener listener);

}
