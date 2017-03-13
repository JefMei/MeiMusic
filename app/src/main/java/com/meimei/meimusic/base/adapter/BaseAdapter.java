package com.meimei.meimusic.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private DataController<T> mDataController = new DataController<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent,viewType);
    }

    protected abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return mDataController.getDataSize();
    }

    //清空数据，并加载
    public boolean loadData(List<T> data){
        mDataController.setData(data);
        notifyDataSetChanged();
        return true;
    }

    public boolean addData(T data){
        if (!mDataController.isContains(data)){
            mDataController.addData(data);
            notifyDataSetChanged();
        }
        return true;
    }

    public boolean clearData(){
        if (mDataController.getDataSize() > 0){
            mDataController.clearData();
            notifyDataSetChanged();
        }
        return true;
    }
}
