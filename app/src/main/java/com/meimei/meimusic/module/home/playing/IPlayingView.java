package com.meimei.meimusic.module.home.playing;

import com.meimei.meimusic.base.view.BaseView;

/**
 * Created by 梅梅 on 2017/5/6.
 */
public interface IPlayingView extends BaseView{

    void setPlayedTime(String playedTime);
    void setDurationTime(String durationTime);
    void setFirstSeekbar();
    void setSeekbar();
    void updateSeekbarFailure();
}
