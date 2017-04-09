package com.meimei.meimusic.module.main.songs;

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
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.adapter.BaseAdapter;
import com.meimei.meimusic.entity.SongList;
import com.meimei.meimusic.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public class SongsAdapter extends BaseAdapter<SongList.Song>{

    private Context mContext;

    public SongsAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new SongsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyc_songs,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SongsViewHolder viewHolder = (SongsViewHolder) holder;
        viewHolder.setData(getDataController().getData(position));

        if (position%2 == 0){
            viewHolder.setMargin();
        }
    }

    class SongsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.linearlayout_parent)
        LinearLayout mLayout;
        @BindView(R.id.tv_item_recommend_num)
        TextView mNum;
        @BindView(R.id.tv_item_song_title)
        TextView mTitle;
        @BindView(R.id.image_item_songs)
        ImageView mSongsImage;

        public SongsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void setData(SongList.Song songInfo){
            Glide.with(mContext)
                    .load(songInfo.pic_300)
                    .into(mSongsImage);
            mNum.setText(songInfo.listenum + "");
            mTitle.setText(songInfo.title);
        }

        private void setMargin(){
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) mLayout.getLayoutParams();
            params.setMargins(0,0,DensityUtil.dp2px(mContext,2),0);
        }
    }
}
