package com.meimei.meimusic.module.main.individuality;

import com.meimei.meimusic.base.view.BaseView;
import com.meimei.meimusic.entity.Individuality;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public interface IIndividualityView extends BaseView{
    void requestRecomSongSuccess(List<Individuality.Recommend_Item> list);
    void requestRecomSongError(String errorInfo);
}
