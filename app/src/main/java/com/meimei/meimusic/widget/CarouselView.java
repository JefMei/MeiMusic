package com.meimei.meimusic.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/19.
 */
public class CarouselView extends RelativeLayout{

    private ViewPager mViewPager;

    private List<View> mViews;

    private CarouselAdapter mAdapter;

    public CarouselView(Context context) {
        this(context,null);
    }

    public CarouselView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        mViewPager = new ViewPager(getContext());
    }

    public void setAdapter(List<View> views){
        this.mViews = views;
        mAdapter = new CarouselAdapter(mViews);
        mViewPager.setAdapter(mAdapter);
        addView(mViewPager);
    }

    private class PointHintView extends LinearLayout{

        public PointHintView(Context context) {
            this(context,null);
        }

        public PointHintView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }


    }

}
