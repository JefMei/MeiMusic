package com.meimei.meimusic.http;

import android.content.res.Resources;

import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.R;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class ApiUtil {

    public static String getBaseUrl(){
        Resources resources = MyApplication.getContext().getResources();
        return resources.getString(R.string.base_url);
    }

    public static <T> T createApi(Class clazz,String baseUrl){
        return ApiFactory.createApi(clazz, baseUrl);
    }
}
