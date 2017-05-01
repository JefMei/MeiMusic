package com.meimei.meimusic.utils;

import android.widget.Toast;

import com.meimei.meimusic.MyApplication;

/**
 * Created by 梅梅 on 2017/4/23.
 */
public class ToastUtil {

    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;

    public static void show(String s){
        Toast.makeText(MyApplication.getContext(),s,Toast.LENGTH_SHORT);
    }

    public static void show(String s,int time){
        Toast.makeText(MyApplication.getContext(),s,time);
    }
}
