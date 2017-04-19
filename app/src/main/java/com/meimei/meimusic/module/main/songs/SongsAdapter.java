package com.meimei.meimusic.module.main.songs;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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
import com.meimei.meimusic.base.adapter.ImageAdapter;
import com.meimei.meimusic.entity.SongList;
import com.meimei.meimusic.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public class SongsAdapter extends ImageAdapter<SongList.Song> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 1;

    private Context mContext;

    public SongsAdapter(Context context) {
        mContext = context;
    }

    private View.OnTouchListener onSongImageListener = getOnTouchListener();

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_NORMAL){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyc_songs,parent,false);
            return new SongsViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyc_songs_footer,parent,false);
            return new FootViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof SongsViewHolder){

            SongsViewHolder viewHolder = (SongsViewHolder) holder;
            viewHolder.setData(getDataController().getData(position));

            if (position%2 == 0){
                viewHolder.setMargin();
            }
        }else if (holder instanceof FootViewHolder){

            FootViewHolder viewHolder = (FootViewHolder) holder;
            viewHolder.startLoadingAnimation();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getDataController().getData(position) == null){
            return TYPE_FOOTER;
        }else
            return TYPE_NORMAL;
    }

    /**
     * 重写此方法用于实现不规则item,使得 loadingItem 占据一排(2个格子)
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_FOOTER){
                        return gridLayoutManager.getSpanCount();    //返回一行所占的格子数
                    }else {
                        return 1;
                    }
                }
            });
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
            mTitle.setText(songInfo.title);
            if (Integer.parseInt(songInfo.listenum) > 10000){
                mNum.setText(Integer.parseInt(songInfo.listenum)/10000 + "万" );
            }else {
                mNum.setText(songInfo.listenum);
            }

            mSongsImage.setOnTouchListener(onSongImageListener);
        }

        private void setMargin(){
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) mLayout.getLayoutParams();
            params.setMargins(0,0,DensityUtil.dp2px(mContext,2),0);
        }

    }


    class FootViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_song_loading)
        ImageView mLoadingImage;

        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void startLoadingAnimation(){
            ((AnimationDrawable)mLoadingImage.getBackground()).start();
        }
    }
}
