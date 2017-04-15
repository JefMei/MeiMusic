package com.meimei.meimusic.module.main.rankinglist;

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
 * Created by 梅梅 on 2017/4/15.
 */
public class SongInfoAdapter extends BaseAdapter<RankingList.songList> {
    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new SongInfoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyc_songinfo_ranking, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SongInfoViewHolder) holder).setData(position, getDataController().getData(position));
    }

    class SongInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_ranking_songinfo)
        TextView mSongInfo;

        public SongInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setData(int position, RankingList.songList songInfo) {
            mSongInfo.setText((position+1) + "." + songInfo.title + " - " + songInfo.author);
        }
    }
}
