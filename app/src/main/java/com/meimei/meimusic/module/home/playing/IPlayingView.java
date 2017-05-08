package com.meimei.meimusic.module.home.playing;

import com.meimei.meimusic.base.view.BaseView;

/**
 * Created by 梅梅 on 2017/5/6.
 */
public interface IPlayingView extends BaseView{

    //设置 seekBar
    void setPlayedTime(String playedTime);
    void setDurationTime(String durationTime);
    void setFirstSeekbar();
    void setSeekbar();
    void updateSeekbarFailure();

    //设置高斯背景图，圆图等
    void setBlurBg(String url);
}
