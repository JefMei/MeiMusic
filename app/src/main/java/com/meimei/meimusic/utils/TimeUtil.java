package com.meimei.meimusic.utils;

/**
 * Created by 梅梅 on 2017/5/6.
 */
public class TimeUtil {

    public static String formatTime(int ms){
        int seconds = ms / 1000;
        int minutes = 0;

        StringBuffer s = new StringBuffer();

        if (seconds >= 60){
            minutes = seconds / 60;
            seconds = seconds % 60;

            if (minutes > 10){
                s.append(minutes);
            }else {
                s.append("0" + minutes);
            }
            s.append(":");

            if (seconds >= 10){
                s.append(seconds);
            }else {
                s.append("0" + seconds);
            }

        }else {
            s.append("00:" );
            if (seconds >= 10){
                s.append(seconds);
            }else {
                s.append("0" + seconds);
            }
        }

        return s.toString();


    }

}
