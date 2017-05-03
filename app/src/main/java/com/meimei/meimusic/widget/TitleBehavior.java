package com.meimei.meimusic.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.R;
import com.meimei.meimusic.utils.DensityUtil;
import com.meimei.meimusic.utils.LogUtil;

/**
 * Created by 梅梅 on 2017/4/19.
 */
public class TitleBehavior extends CoordinatorLayout.Behavior<Toolbar>{

    private Context mContext;
    private int offsetTotal = 0;    //总共滑动的距离

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
     * @param dyConsumed    在y轴上，滑动了的距离
     * @param dxUnconsumed
     * @param dyUnconsumed  在y轴上，滑动到底部或顶部后，又继续滑的距离
     * */

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        if (dyUnconsumed == 0){
            offsetTotal += dyConsumed;
        }

        LogUtil.i("tag","dyUnconsumed:"+dyUnconsumed);
        LogUtil.i("tag","dyConsumed:"+dyConsumed);
        LogUtil.i("tag","offsetTotal:"+offsetTotal);

        if (offsetTotal > DensityUtil.dp2px(MyApplication.getContext(),100) &&
                offsetTotal < (target.findViewById(R.id.relativelayout_ranking_official_header)).getHeight()){

//            ((TextView)child.findViewById(R.id.tv_title_toolbar_ranking_official)).setText("云音乐新歌榜");

        }else if (offsetTotal < DensityUtil.dp2px(MyApplication.getContext(),100)){
//            ((TextView)child.findViewById(R.id.tv_title_toolbar_ranking_official)).setText("");
        }else if (offsetTotal > (target.findViewById(R.id.relativelayout_ranking_official_header)).getHeight()){
//            child.setBackground(new ColorDrawable(R.color.toolbar_bg_normal_official_new_music));
//            child.setBackgroundColor(R.color.colorPrimary);
        }

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {

    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, Toolbar child, View target, float velocityX, float velocityY, boolean consumed) {
        return true;
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
