package com.meimei.meimusic.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.R;
import com.meimei.meimusic.utils.DensityUtil;

/**
 * Created by 梅梅 on 2017/4/19.
 */
public class TitleBehavior extends CoordinatorLayout.Behavior<Toolbar>{

    private Context mContext;

    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return  返回true 后续的滑动事件才能收到
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    /**
     * 处理滑动事件
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {


        if (dyConsumed > DensityUtil.dp2px(MyApplication.getContext(),10)){
//            child.setBackground(new ColorDrawable(MyApplication.getContext().getResources().getColor(R.color.colorPrimary)));
            ((TextView)child.findViewById(R.id.tv_title_toolbar_ranking_official)).setText("云音乐新歌榜");

        }

    }


    /**
     *
     * @param parent
     * @param child     是指要执行动作的CoordinatorLayout的子View
     * @param dependency    是指Child依赖的View
     * @return  返回子View Toolbar 是否依赖 View dependency
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {

        return super.layoutDependsOn(parent,child,dependency);
    }

    /**
     * 当dependency view 发生移动时，该方法调用
     * @param parent
     * @param child
     * @param dependency
     * @return  返回true表示子View位置或宽高发生改变，返回false表示不变
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {

        return super.onDependentViewChanged(parent,child,dependency);
    }

}
