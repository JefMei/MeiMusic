package com.meimei.meimusic.base.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class DataController<T> {

    private List<T> mDataList = new ArrayList<>();

    public boolean addDataList(List<T> data){
        mDataList.addAll(data);
        return true;
    }

    public boolean addData(T data){
        mDataList.add(data);
        return true;
    }

    public boolean updateData(List<T> data){
        clearData();
        mDataList.addAll(data);
        return true;
    }

    public boolean clearData(){
        mDataList.clear();
        return true;
    }

    public boolean remove(int position){
        if (position < getDataSize()){
            mDataList.remove(position);
            return true;
        }
        return false;
    }

    public T getData(int position){
        return mDataList.get(position);
    }

    public List<T> getDataList(){
        return mDataList;
    }

    public int getDataSize(){
        return mDataList.size();
    }

    public boolean isContains(T data){
        return mDataList.contains(data);
    }


}
