package com.meimei.meimusic.module.main.individuality;

import android.view.View;
import android.widget.ImageView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.widget.CarouselView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class IndividualityFragment extends BaseFragment{

    private final int[] imageRes = {R.mipmap.ic_vp_first,R.mipmap.ic_vp_second,R.mipmap.ic_vp_third,
            R.mipmap.ic_vp_fourth,R.mipmap.ic_vp_five,R.mipmap.ic_vp_six,R.mipmap.ic_vp_seven};

    private List<View> mViews = new ArrayList<>();

    private CarouselView mVpView;

    @Override
    protected void initView() {
        for (int i=0;i<7;i++){
            ImageView ima = new ImageView(getActivity());
            ima.setScaleType(ImageView.ScaleType.CENTER);
            ima.setImageResource(imageRes[i]);
            mViews.add(ima);
        }
        mVpView = (CarouselView) getView().findViewById(R.id.carouseview_individuality);
        mVpView.setAdapter(mViews);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_individuality;
    }

    public static IndividualityFragment newInstance(){
        IndividualityFragment individualityFragment = new IndividualityFragment();
        return individualityFragment;
    }
}
