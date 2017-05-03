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
import com.meimei.meimusic.entity.Song;
import com.meimei.meimusic.http.ApiUtil;
import com.meimei.meimusic.http.OkHttpUtil;
import com.meimei.meimusic.http.api.Api;
import com.meimei.meimusic.module.main.callback.OnPlaySongListener;
import com.meimei.meimusic.utils.key.AESTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicAdapter extends BaseAdapter<RankingList.songList>{

    private final int ITEM_HEADER = 0;
    private final int ITEM_NORMAL = 1;
    private final String TAG = "NewMusicAdapter";

    private OnPlaySongListener onPlaySongListener;

    public NewMusicAdapter(OnPlaySongListener onPlaySongListener) {
        this.onPlaySongListener = onPlaySongListener;
    }

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
            viewHolder.setData(getDataController().getData(position),onPlaySongListener);

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

        private final String TAG = "NewMusicViewHolder";

        private Api mApi;
        private long songId;
        private OnPlaySongListener onPlaySongListener;

        public NewMusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mApi = ApiUtil.createApi(Api.class,ApiUtil.getBaseUrl());
            itemView.setOnClickListener(onClickListener);
        }

        private void setData(RankingList.songList songInfo,OnPlaySongListener listener){
            mNum.setText((getAdapterPosition()) + "");
            mName.setText(songInfo.title);
            mAuthor.setText(songInfo.author + " - " + songInfo.title);
            songId = songInfo.song_id;
            onPlaySongListener = listener;
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "songid=" + songId + "&ts=" + System.currentTimeMillis();
                String key = AESTools.encrpty(str);

                StringBuffer url = new StringBuffer(ApiUtil.getBaseUrl());
                url.append("v1/restserver/ting?" +
                        "from=android&version=5.6.5.6&format=json&method=baidu.ting.song.getInfos&");
                url.append(str);
                url.append("&e=").append(key);

                String s = url.toString().trim();

                 OkHttpUtil.requestForGet(url.toString().trim(), new OkHttpUtil.OnSongUrlCallback() {
                     @Override
                     public void onSuccess(Song.UrlInfo songUrl) {
                         if (onPlaySongListener != null){
                             onPlaySongListener.play("" + songUrl.show_link,getAdapterPosition()-1);
                         }
                     }

                     @Override
                     public void onFailure(String failure) {

                         if (onPlaySongListener != null){
                             onPlaySongListener.playError(failure);
                         }
                     }
                 });

                /*mApi.getSongInfo(songId+"",System.currentTimeMillis()+"",key)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Song>() {
                            @Override
                            public void accept(@NonNull Song song) throws Exception {

                                if (onPlaySongListener != null){
                                    onPlaySongListener.play("" + song.songurl.url.get(0).show_link,getAdapterPosition());
                                }

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {

                            }
                        });*/
            }
        };
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
