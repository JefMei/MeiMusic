package com.meimei.meimusic.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class Individuality {

    public Result result;
    public int error_code;
    public List<Module> module;

    public static class Result{
        public Radio radio;
        public Recommend diy;
        public NewMusic mix_1;
    }

    public static class Module{
        public String link_url;
        public int pos;
        public String title;
        public String key;
        public String picurl;
        public String title_more;
        public int style;
        public String jump;
    }

    public static class Radio{
        public int error_code;
        public List<Radio_Item> result;

    }

    public static class Radio_Item{
        public String desc;
        public String itemid;
        public String title;
        public String album_id;
        public String type;
        public String channelid;
        public String pic;
    }

    public static class Recommend{
        public int error_code;
        public List<Recommend_Item> result;
    }

    public static class Recommend_Item{
        public int position;
        public String tag;
        //songidlist
        public String pic;
        public String title;
        public int collectnum;
        public String type;
        public int listenum;
        public String listid;
    }

    public static class NewMusic{
        public int error_code;
        public List<NewMusic_Item> result;
    }

    public static class NewMusic_Item{
        public String desc;
        public String pic;
        public String type_id;
        public int type;
        public String title;
        public int tip_type;
        public String author;
    }

}
