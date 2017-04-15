package com.meimei.meimusic.module.main.rankinglist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meimei.meimusic.R;
import com.meimei.meimusic.base.adapter.BaseAdapter;
import com.meimei.meimusic.entity.RankingList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public class RankingAdapter extends BaseAdapter<RankingList.songList> {

    private int pic[] = {R.mipmap.ic_ranklist_six, R.mipmap.ic_ranklist_first, R.mipmap.ic_ranklist_second, R.mipmap.ic_ranklist_third
            , R.mipmap.ic_ranklist_acg};

    private Context mContext;



    public RankingAdapter(Context context) {
        mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new RankingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rercyc_ranking, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List songInfoList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            songInfoList.add(getDataController().getData(position * 3 + i));
        }

        ((RankingViewHolder) holder).setData(position, songInfoList);
    }

    @Override
    public int getItemCount() {
        return getDataController().getDataSize() / 3;
    }

    class RankingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_ranking)
        ImageView mImage;
        @BindView(R.id.recyc_ranking_songs)
        RecyclerView mSongInfo;

        public RankingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setData(int position, List<RankingList.songList> songList) {
            Glide.with(mContext)
                    .load(pic[position])
                    .into(mImage);

            SongInfoAdapter adapter = new SongInfoAdapter();
            mSongInfo.setLayoutManager(new LinearLayoutManager(mContext));
            mSongInfo.setAdapter(adapter);
            adapter.loadData(songList);
        }

    }
}
