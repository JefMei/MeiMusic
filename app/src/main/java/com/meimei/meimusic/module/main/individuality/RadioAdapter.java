package com.meimei.meimusic.module.main.individuality;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.adapter.ImageAdapter;
import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class RadioAdapter extends ImageAdapter<Individuality.Radio_Item> {

    private Context mContext;

    private View.OnTouchListener onSongImageListener = getOnTouchListener();

    public RadioAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new RadioViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyc_radio,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RadioViewHolder mViewHolder = (RadioViewHolder) holder;
        mViewHolder.setData(getDataController().getData(position));
        if (position%3 == 1){
            mViewHolder.setMagin();
        }
    }

    class RadioViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.linearlayout_parent)
        LinearLayout mLayoutParent;
        @BindView(R.id.image_item_radio)
        ImageView mPic;
        @BindView(R.id.tv_item_radio_introdution)
        TextView mIntrodution;

        public RadioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Individuality.Radio_Item data){
            Glide.with(mContext)
                    .load(data.pic)
                    .placeholder(R.mipmap.ic_placeholder_disk)
                    .into(mPic);
            mIntrodution.setText(data.title);

            mPic.setOnTouchListener(onSongImageListener);
        }

        public void setMagin(){
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) mLayoutParent.getLayoutParams();
            params.setMargins(DensityUtil.dp2px(MyApplication.getContext(),2),0,DensityUtil.dp2px(MyApplication.getContext(),2),0);
        }
    }

}
