package com.meimei.meimusic.module.main.rankinglist.newmusic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.adapter.BaseAdapter;
import com.meimei.meimusic.entity.RankingList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicAdapter extends BaseAdapter<RankingList.songList>{

    private final int ITEM_HEADER = 0;
    private final int ITEM_NORMAL = 1;

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            return new NewMusicHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_recyc_header_ranking_official,parent,false));
        }else {
            return new NewMusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_recyc_ranking_official,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NewMusicViewHolder){

            NewMusicViewHolder viewHolder = (NewMusicViewHolder) holder;
            viewHolder.setData(getDataController().getData(position),position);

        }

        if (holder instanceof NewMusicHeaderViewHolder){

            NewMusicHeaderViewHolder viewHolder = (NewMusicHeaderViewHolder) holder;
            viewHolder.setData(getDataController().getDataSize() - 1);  //j减掉头Item
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_HEADER;
        }else {
            return ITEM_NORMAL;
        }
    }

    public static class NewMusicViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item_ranking_official_num)
        TextView mNum;
        @BindView(R.id.tv_item_ranking_official_name)
        TextView mName;
        @BindView(R.id.tv_item_ranking_official_author)
        TextView mAuthor;
        @BindView(R.id.image_item_ranking_official_more)
        ImageView mMoreInfo;

        public NewMusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void setData(RankingList.songList songInfo,int position){
            mNum.setText((position) + "");
            mName.setText(songInfo.title);
            mAuthor.setText(songInfo.author + " - " + songInfo.title);
        }
    }

    public static class NewMusicHeaderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.relativelayout_playall_ranking_official)
        RelativeLayout mPlayAll;
        @BindView(R.id.linearlayout_more_choice_ranking_official)
        LinearLayout mMoreChoice;
        @BindView(R.id.tv_songnum_ranking_official)
        TextView mSongNum;

        public NewMusicHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void setData(int songNum){
            mSongNum.setText("(" + songNum + ")");
        }

        private void setOnClick(){

        }
    }
}
