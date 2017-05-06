package com.meimei.meimusic.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.meimei.meimusic.service.IMusicBinder;
import com.meimei.meimusic.service.MusicService;

/**
 * Created by 梅梅 on 2017/4/17.
 */
public class MusicUtil {

    //飙升榜
    public static final int BILLBOARD_NET_MUSIC = 25;
    //新歌榜
    public static final int BILLBOARD_NEW_MUSIC = 1;
    //原创音乐榜
    public static final int BILLBOARD_ORIGINAL = 200;
    //热歌榜
    public static final int BILLBOARD_HOT_MUSIC = 2;
    //ACG音乐榜
    public static final int BILLBOARD_KING = 100;
    //欧美金曲榜
    public static final int BILLBOARD_EU_UK = 21;
    //经典老歌榜
    public static final int BILLBOARD_CLASSIC_OLD = 22;

    public static final int OFFSET = 0;

    public static final String FIELDS= "song_id,title,author,album_title,pic_big,pic_small,havehigh,all_rate,charge,has_mv_mobile,learn,song_source,korean_bb_song";

    private static IMusicBinder musicBinder;
    private static MusicServiceConnection musicConnetion;

    private static boolean isPlaying = false;

    private static boolean isFirstPlay = true;   //用来记录打开App后，是否有播放过歌曲，如果播放过则就不再记录上次的退出时的播放位置


    private static SongInfo mSongInfo;

    public static void bindService(Context context){
        Intent intent = new Intent(context, MusicService.class);
        musicConnetion = new MusicServiceConnection();
        context.bindService(intent,musicConnetion,context.BIND_AUTO_CREATE);

        LogUtil.i(""+context.getClass(),"bindservice");
    }

    public static void unbindService(Context context){
        context.unbindService(musicConnetion);
    }

    private static class MusicServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (IMusicBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public static void playMusic(String url,SongInfo songInfo){
        musicBinder.playMusic(url);
        mSongInfo = songInfo;
        isFirstPlay = false;
        isPlaying = true;
    }

    public static void seekTo(String url,SongInfo songInfo ,int position){
        musicBinder.seekTo(url,position);
        mSongInfo = songInfo;
        isFirstPlay = false;
        isPlaying = true;
    }

    public static void play(){
        if (musicBinder != null){
            musicBinder.play();
            isPlaying = true;
        }else {

        }
    }

    public static void pause(){
        if (musicBinder != null){
            musicBinder.pause();
            isPlaying = false;
        }else {

        }
    }

    public static void playOrPause() {
        if (musicBinder != null) {
            if (musicBinder.isPlaying()) {
                musicBinder.pause();
                isPlaying = false;
            } else {
                musicBinder.play();
                isPlaying = true;
            }
        }
    }

    public static void stop() {
        if (musicBinder != null) {
            musicBinder.stop();
            isPlaying = false;
        }
    }

    public static void seekTo(int position){
        if (musicBinder != null){
            musicBinder.seekTo(position);
            isPlaying = true;
        }
    }

    public static boolean isPlaying() {
        return isPlaying;
    }

    public static int getDuration() {

        if (musicBinder != null) {
            return musicBinder.getDuration();
        }
        return 0;
    }

    public static int getCurrentPosition() {

        if (musicBinder != null) {
            return musicBinder.getCurrentPosition();
        }
        return 0;
    }

    public static boolean isFirstPlay(){
        return isFirstPlay;
    }

    public static SongInfo getSongInfo(){
        if (mSongInfo == null){
            return new SongInfo();
        }
        return mSongInfo;
    }

    public static void putSongInfo(SongInfo songInfo){
        mSongInfo = songInfo;
    }

    public static class SongInfo{
        public String songName;
        public String singer;
    }

}
