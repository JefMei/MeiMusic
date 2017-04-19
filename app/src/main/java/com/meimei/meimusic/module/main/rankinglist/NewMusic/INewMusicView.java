package com.meimei.meimusic.module.main.rankinglist.newmusic;

import com.meimei.meimusic.base.view.BaseView;
import com.meimei.meimusic.entity.RankingList;

import java.util.List;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public interface INewMusicView extends BaseView{

//    显示 或 隐藏 loading动画
    void showLoading();
    void hideLoading();

//    显示 或 隐藏 连接网络失败 提示
    void showNetError();
    void hideNetError();

    void showNewMusicList(List<RankingList.songList> songLists);

}
