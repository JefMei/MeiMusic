package com.meimei.meimusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class MyApplication extends Application{

    private static Context mContext;

    public MyApplication() {
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
