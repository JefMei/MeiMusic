package com.meimei.meimusic.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.R;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.utils.MusicUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/4/23.
 */
public abstract class BottomBarActivity extends BaseActivity{

    @BindView(R.id.image_main_song_pic)
    protected ImageView mBtnSongPic;
    @BindView(R.id.tv_main_song_name)
    protected TextView mBtnSongName;
    @BindView(R.id.tv_main_song_author)
    protected TextView mBtnSongAuthor;
    @BindView(R.id.relative_bottom)
    protected RelativeLayout mBtnView;
    //按钮
    @BindView(R.id.image_bottom_playlist)
    protected ImageView mBtnPlayList;
    @BindView(R.id.image_bottom_play)
    protected ImageView mBtnPlay;
    @BindView(R.id.image_bottom_next)
    protected ImageView mBtnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void updateBottomView(RankingList.songList songInfo){

        Glide.with(this)
                .load(songInfo.pic_small)
                .into(mBtnSongPic);
        mBtnSongName.setText(songInfo.title);
        mBtnSongAuthor.setText(songInfo.author);

        Glide.with(this)
                .load(R.mipmap.ic_pause)
                .into(mBtnPlay);
    }

    protected abstract void onBottomViewClick();

    @OnClick(R.id.relative_bottom)
    void doBottomView(){
        onBottomViewClick();
    }

    @OnClick(R.id.image_bottom_playlist)
    void doPlayList(){

    }

    @OnClick(R.id.image_bottom_play)
    void doPlay(){

        if (MusicUtil.isPlaying() == true){

            Glide.with(this)
                    .load(R.mipmap.ic_play)
                    .into(mBtnPlay);
            MusicUtil.pause();

        }else {

            Glide.with(this)
                    .load(R.mipmap.ic_pause)
                    .into(mBtnPlay);
            MusicUtil.play();
        }

    }

    @OnClick(R.id.image_bottom_next)
    void doNext(){

    }

}
