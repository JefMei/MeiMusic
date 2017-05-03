package com.meimei.meimusic.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.R;
import com.meimei.meimusic.entity.RankingList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/4/23.
 */
public abstract class BottomBarActivity extends BaseActivity{

    @BindView(R.id.image_main_song_pic)
    protected ImageView mBottomSongPic;
    @BindView(R.id.tv_main_song_name)
    protected TextView mBottomSongName;
    @BindView(R.id.tv_main_song_author)
    protected TextView mBottomSongAuthor;
    @BindView(R.id.relative_bottom)
    protected RelativeLayout mBottomView;
    //按钮
    @BindView(R.id.image_bottom_playlist)
    protected ImageView mBottomPlayList;
    @BindView(R.id.image_bottom_play)
    protected ImageView mBottomPlay;
    @BindView(R.id.image_bottom_next)
    protected ImageView mBottomNext;

    protected boolean isPlaying = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    protected void updateBottomView(RankingList.songList songInfo){

        isPlaying = true;

        Glide.with(this)
                .load(songInfo.pic_small)
                .into(mBottomSongPic);
        mBottomSongName.setText(songInfo.title);
        mBottomSongAuthor.setText(songInfo.author);

        Glide.with(this)
                .load(R.mipmap.ic_pause)
                .into(mBottomPlay);
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

        if (isPlaying == true){
            isPlaying = false;
            Glide.with(this)
                    .load(R.mipmap.ic_play)
                    .into(mBottomPlay);
            getMusicBinder().pause();

        }else {
            isPlaying = true;
            Glide.with(this)
                    .load(R.mipmap.ic_pause)
                    .into(mBottomPlay);
            getMusicBinder().start();
        }

    }

    @OnClick(R.id.image_bottom_next)
    void doNext(){

    }

}
