package com.meimei.meimusic.module.main.individuality;

import com.meimei.meimusic.base.view.BaseView;
import com.meimei.meimusic.entity.Individuality;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public interface IIndividualityView extends BaseView{

    //请求推荐歌单接口数据回调
    void requestRecomSongSuccess(List<Individuality.Recommend_Item> list);
    void requestRecomSongError(String errorInfo);

    //请求电台接口数据回调
    void requestRadioSuccess(List<Individuality.Radio_Item> list);
    void requestRadioError(String errorInfo);

    //请求新歌接口数据回调
    void requestNewMusicSuccess(List<Individuality.NewMusic_Item> list);
    void requestNewMusicError(String errorInfo);

    //请求

}
