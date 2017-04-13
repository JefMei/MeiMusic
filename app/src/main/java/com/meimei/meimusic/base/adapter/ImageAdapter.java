package com.meimei.meimusic.base.adapter;

import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 梅梅 on 2017/4/13.
 */
public abstract class ImageAdapter<T> extends BaseAdapter<T>{

    private View.OnTouchListener onSongImageListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView view = (ImageView) v;

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    view.getDrawable().setColorFilter(0x15000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
            }
            return true;
        }
    };

    public View.OnTouchListener getOnTouchListener(){
        return onSongImageListener;
    }

}
