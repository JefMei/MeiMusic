package com.meimei.meimusic.module.main.individuality;

import com.meimei.meimusic.base.model.BaseModel;
import com.meimei.meimusic.module.main.callback.OnIndividualityListener;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public interface IIndividualityModel extends BaseModel{

    void requestIndividualityForNet(OnIndividualityListener listener);
}
