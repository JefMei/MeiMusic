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
        public int down_type;
        public int original;
        public int free;
        public String replay_gain;
        public int song_file_id;
        public String file_size;
        public String file_extension;
        public int file_duration;
        public int can_see;
        public boolean can_load;
        public double preload;
        public int file_bitrate;
        public String file_link;
        public int is_udition_url;
        public String hash;

    }

}
