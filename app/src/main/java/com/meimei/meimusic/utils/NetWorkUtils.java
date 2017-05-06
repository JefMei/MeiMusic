package com.meimei.meimusic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.meimei.meimusic.MyApplication;

/**
 * Created by 梅梅 on 2017/2/3.
 */
public class NetWorkUtils {


    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {

        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断NetworkInfo对象是否为空
        if (networkInfo != null)
            return networkInfo.isAvailable();

        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @return
     */
    public static boolean isWifiConnected() {

        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断NetworkInfo对象是否为空 并且类型是否为WIFI
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            return networkInfo.isAvailable();

        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     */
    public static boolean isMobileConnected() {

        //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            return networkInfo.isAvailable();

        return false;
    }


}
