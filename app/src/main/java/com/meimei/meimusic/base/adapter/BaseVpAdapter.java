package com.meimei.meimusic.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/19.
 */
public class BaseVpAdapter extends PagerAdapter{

    private List<View> mViews;

    public BaseVpAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = mViews.get(position);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mViews != null? mViews.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
