package com.meimei.meimusic.module.main.rankinglist.newmusic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new NewMusicViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_recyc_ranking_new_music,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewMusicViewHolder viewHolder = (NewMusicViewHolder) holder;

        viewHolder.setData(getDataController().getData(position),position);
    }

    public static class NewMusicViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item_ranking_new_music_num)
        TextView mNum;
        @BindView(R.id.tv_item_ranking_new_music_name)
        TextView mName;
        @BindView(R.id.tv_item_ranking_new_music_author)
        TextView mAuthor;

        public NewMusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void setData(RankingList.songList songInfo,int position){
            mNum.setText(position + "");
            mName.setText(songInfo.title);
            mAuthor.setText(songInfo.author);
        }

    }
}
