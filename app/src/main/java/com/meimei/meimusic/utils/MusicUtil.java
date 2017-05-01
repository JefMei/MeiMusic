package com.meimei.meimusic.utils;

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

    private MusicUtil() {

    }

}
