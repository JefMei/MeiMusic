package com.meimei.meimusic;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class MyApplication extends Application{

    private static Context mContext;

    private static Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getContext(){
        return mContext;
    }

    public static Gson getGsonInstance(){
        if (mGson == null){
            mGson = new Gson();
        }
        return mGson;
    }
}
