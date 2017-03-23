package com.meimei.meimusic.module.main.callback;

import com.meimei.meimusic.entity.Individuality;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public interface OnIndividualityListener {

    void success(Individuality individuality);
    void error(Throwable throwable);
}
