package com.meimei.meimusic.base.presenter;

import com.meimei.meimusic.base.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BasePresenter<T extends BaseView> {

    private WeakReference<T> mReference;

    public BasePresenter(T baseView) {
        mReference = new WeakReference<T>(baseView);
    }

    public T getView(){
        if (mReference != null){
            return mReference.get();
        }
        return null;
    }

    public void detachView(){
        mReference.clear();
        mReference = null;
    }

}
