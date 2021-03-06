package com.meimei.meimusic.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 梅梅 on 2017/5/2.
 */
public class IntentUtil {

    public static final String SINGER = "singer";
    public static final String SONGNAME = "songName";
    public static final String ISPLAYING = "isPlaying";

    public static void startActivity(Activity context1,Class clas){
        startActivity(context1,clas,null);
    }

    public static void startActivity(Activity context1, Class clas, Bundle bundle){

        Intent intent = new Intent(context1,clas);

        if (bundle != null){
            intent.putExtras(bundle);
        }

        context1.startActivity(intent);
    }

    public static void startActivityWithTransition(Activity context1, Class clas, Bundle bundle){
        if (bundle == null){
            startActivity(context1,clas);
        }
        Intent intent = new Intent(context1,clas);

        context1.startActivity(intent,bundle);

    }

    public static void startActivityForResult(Activity activity, Class clas){

        startActivityForResult(activity,clas,null);
    }

    public static void startActivityForResult(Activity activity, Class clas, Bundle bundle){

        Intent intent = new Intent(activity,clas);

        if (bundle != null){
            intent.putExtras(bundle);
        }

        activity.startActivityForResult(intent,0);
    }

}
