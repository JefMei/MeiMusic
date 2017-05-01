package com.meimei.meimusic.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/4/23.
 */
public class Song {

    public SongUrl songurl;
    public int error_code;
    public String error_message;

    public static class SongUrl{
        public List<UrlInfo> url;
    }

    public static class UrlInfo{
        public String show_link;
        public double preload;
    }

}
