package com.meimei.meimusic.module.main.individuality;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.adapter.BaseAdapter;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class ExclusiveAdapter extends BaseAdapter{

    private Context mContext;

    public ExclusiveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new ExclusiveViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyc_exclusive,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ExclusiveViewHolder extends RecyclerView.ViewHolder{

        public ExclusiveViewHolder(View itemView) {
            super(itemView);
        }
    }
}
