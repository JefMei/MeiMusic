package com.meimei.meimusic.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.meimei.meimusic.R;
import com.meimei.meimusic.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/3/19.
 */
public class CarouselView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    private List<View> mViews = new ArrayList<>();

    private View[] mPoints;

    private CarouselAdapter mAdapter;

    private int mCurrentItem;

    private boolean isCycle = true;

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
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 1、根据循环条件isCycle()判断是否循环，给mView内add轮播图片的数据
     * 2、setApdater()
     * @param imageRes  轮播图片资源文件列表
     */
    public void setData(List<Integer> imageRes){

        if (isCycle()){
            mViews.add(newCycleView(imageRes.get(imageRes.size()-1)));
            for (int i=0;i<imageRes.size();i++){
                mViews.add(newCycleView(imageRes.get(i)));
            }
            mViews.add(newCycleView(imageRes.get(0)));
        }else {
            for (int i=0;i<imageRes.size();i++){
                mViews.add(newCycleView(imageRes.get(i)));
            }
        }

        setAdapter();
    }

    /**
     * 1、将轮播的数据加入adapter
     * 2、给Viewpager 设置 adapter
     * 3、将viewpager addview 到布局
     * 4、addPointView() :将指示器PointView addview 到布局
     * 5、根据是否轮播，设置第一页
     */
    public void setAdapter(){

        mAdapter = new CarouselAdapter(mViews);
        mViewPager.setAdapter(mAdapter);
        addView(mViewPager);
        addPointView();

        if (isCycle()){
            mViewPager.setCurrentItem(1);
        }else {
            mViewPager.setCurrentItem(0);
        }

        autoCycle();
    }

    /**
     * 完成自动滚动轮播图片
     */
    private void autoCycle(){
        Observable.interval(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mCurrentItem++;
                        mViewPager.setCurrentItem(mCurrentItem);

                    }
                });
    }

    /**
     * 根据传入的Res，new一个ImageView，做好初始化工作，返回这个ImageView
     * @param imageRes  res资源文件
     * @return  一个定制的ImageView
     */
    private ImageView newCycleView(int imageRes){
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(imageRes);
        return imageView;
    }

    public boolean isCycle(){
        return isCycle;
    }

    /**
     * 1、指示器初始化工作
     * 2、将指示器PointView addview 到布局
     */
    private void addPointView(){
        PointHintView pointView = new PointHintView(mAdapter.getCount()-2,getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pointView.setOrientation(LinearLayout.HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        params.setMargins(0,0,0,DensityUtil.dp2px(getContext(),10));
        pointView.setLayoutParams(params);
        addView(pointView);

    }

    private void setIndicator(int position){
        for (int i=0;i<mPoints.length;i++){
            if (i == position){
                mPoints[i].setSelected(true);
            }else {
                mPoints[i].setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 因为实际的views总数要比指示器总数多2个
     * 所以要用该回调函数，设置正确的指示器位置。
     * @param position  实际滑动的位置
     */
    @Override
    public void onPageSelected(int position) {
        int max = mViews.size()-1;
        mCurrentItem = position;

        if (position == max){
            mCurrentItem = 1;
        }else if (position == 0){
            mCurrentItem = max-1;
        }

        setIndicator(mCurrentItem-1);
    }

    /**
     * 当mCurrentItem=0或8的时候，让ViewPager回归原本1、7的位置。
     * 使得视觉效果上好像是从最后一页滑到了第一页，实际上是在最后一页的后面加了第一页的图片
     * 然后在这个函数将viewpager的item悄无声息的设置为第一页，图片一样，所以看不出来。
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 0){
            mViewPager.setCurrentItem(mCurrentItem,false);
        }
    }

    private class PointHintView extends LinearLayout{


        public PointHintView(int num,Context context) {
            this(num,context,null);
        }

        public PointHintView(int num,Context context, AttributeSet attrs) {
            super(context, attrs);
            initPointView(num);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void initPointView(int num) {
            mPoints = new View[num];
            for (int i=0;i < num;i++){
                View view = new View(getContext());
                LinearLayout.LayoutParams params = new LayoutParams(DensityUtil.dp2px(getContext(),9),DensityUtil.dp2px(getContext(),9));
                params.setMargins(DensityUtil.dp2px(getContext(),2.5f),0,DensityUtil.dp2px(getContext(),2.5f),0);
                view.setBackground(getResources().getDrawable(R.drawable.vp_point_bg));
                view.setLayoutParams(params);
                mPoints[i] = view;
                addView(view);
            }
            mPoints[0].setSelected(true);
        }

    }

}
