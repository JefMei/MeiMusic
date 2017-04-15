package com.meimei.meimusic.module.main.rankinglist;

import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.http.ApiUtil;
import com.meimei.meimusic.http.api.Api;
import com.meimei.meimusic.module.main.callback.OnRankingListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public class RankingModel implements IRankingModel{

    private final String FIELDS = "song_id,title,author,album_title,pic_big,pic_small,havehigh,all_rate,charge,has_mv_mobile,learn,song_source,korean_bb_song";

    private Api mApi;

    public RankingModel() {
        mApi = ApiUtil.createApi(Api.class,ApiUtil.getBaseUrl());
    }


    @Override
    public void requestRankingListForNet(int type, int offset, int size, final OnRankingListener listener) {
        mApi.getRankingList(type,offset,size,FIELDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RankingList>() {
                    @Override
                    public void accept(@NonNull RankingList rankingList) throws Exception {
                        listener.succees(rankingList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.error(throwable);
                    }
                });
    }
}
