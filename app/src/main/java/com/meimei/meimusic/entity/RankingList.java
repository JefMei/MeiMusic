package com.meimei.meimusic.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public class RankingList {

    public List<songList> song_list;
    public BillBoard billboard;

    public static class songList{
        public String title;
        public String author;
    }

    public static class BillBoard{
        public int billboard_type;
    }
}
