package com.meimei.meimusic.base.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class DataController<T> {

    private List<T> mDataList = new ArrayList<>();

    protected boolean addDataList(List<T> data){
        mDataList.addAll(data);
        return true;
    }

    protected boolean addData(T data){
        mDataList.add(data);
        return true;
    }

    protected boolean setData(List<T> data){
        clearData();
        mDataList.addAll(data);
        return true;
    }

    protected boolean clearData(){
        mDataList.clear();
        return true;
    }

    protected T getData(int position){
        return mDataList.get(position);
    }

    protected List<T> getDataList(){
        return mDataList;
    }

    protected int getDataSize(){
        return mDataList.size();
    }

    protected boolean isContains(T data){
        return mDataList.contains(data);
    }

}
