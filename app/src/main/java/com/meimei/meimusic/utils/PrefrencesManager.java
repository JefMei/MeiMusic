package com.meimei.meimusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.meimei.meimusic.MyApplication;

/**
 * Created by 梅梅 on 2017/5/4.
 */
public class PrefrencesManager {

    public static final String SONGNAME = "song_name";
    public static final String SINGER = "singer";
    public static final String PICURL = "pic_url";
    public static final String BIGPICURL = "big_pic_url";
    public static final String SONGURL = "song_url";
    public static final String PLAYEDPOSITION = "played_position";
    public static final String SONGDURATION = "song_duration";

    private Context mContext;
    private static SharedPreferences mPreferences;

    private String mPackName;

    public PrefrencesManager() {
        mContext = MyApplication.getContext();
        mPackName = mContext.getPackageName();
        mPreferences = mContext.getSharedPreferences(mPackName,0);

    }

    public static PrefrencesManager getInstance(){
        return PrefrencesInstance.instance;
    }

    private static class PrefrencesInstance{
        private static final PrefrencesManager instance = new PrefrencesManager();
    }

    public void setString(String key,String value){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key,String defaultValue){
        return  mPreferences.getString(key,defaultValue);
    }

    public void setInt(String key,int value){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInt(String key,int defaultValue){
        return mPreferences.getInt(key,defaultValue);
    }

    public void setBoolean(String key,boolean value){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key){
        return mPreferences.getBoolean(key,false);
    }
}
