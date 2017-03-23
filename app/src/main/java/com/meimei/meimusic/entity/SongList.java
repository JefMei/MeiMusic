package com.meimei.meimusic.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/23.
 */
public class SongList {
    public int error_code;
    public int total;
    public int havamore;
    public List<Song> content;

    public static class Song{
        public String listid;
        public String listenum;
        public String collectnum;
        public String title;
        public String pic_300;
        public String tag;
        public String desc;
        public String pic_w300;
        public String width;
        public String height;
        public List<String> songIds;
    }
}
