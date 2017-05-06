package com.meimei.meimusic.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meimei.meimusic.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BaseFragment extends Fragment{

    private final String IS_HIDDEN = "IS_HIDDEN";

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        LogUtil.i("BaseFragment ->" + getClass() + "Context:", context + "");
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        LogUtil.i("" + getClass() + "activity:", activity + "");
    }*/

    /**
     * 根据savedInstanceState参数来判断是否是从”内存重启“或横屏等异常的情况归来，
     * 如果是从异常情况返回，则savedInstanceState不为空，则通过标识 isHidden
     * 来判断该fragment在发生异常情况之前是显示的还是隐藏的，
     * 由此避免fragment重叠的情况。
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            Boolean isHidden = savedInstanceState.getBoolean(IS_HIDDEN);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (isHidden){
                transaction.hide(this);
            }else {
                transaction.show(this);
            }
            transaction.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    /**
     * 在异常情况退出的情况下会回调此函数
     * 保存本fragment在异常退出情况下的显示状态
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_HIDDEN,isHidden());
    }

    protected abstract void initView();

    protected abstract int getLayoutRes();

}
