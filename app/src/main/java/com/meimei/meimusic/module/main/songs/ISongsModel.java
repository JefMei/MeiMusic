package com.meimei.meimusic.module.main.songs;

import com.meimei.meimusic.base.model.BaseModel;
import com.meimei.meimusic.module.main.callback.OnSongsListener;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public interface ISongsModel extends BaseModel{

    void getSongsForNet(OnSongsListener listener,int pageSize,int pageNo);

}
