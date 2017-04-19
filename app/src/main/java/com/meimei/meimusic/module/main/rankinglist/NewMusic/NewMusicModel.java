package com.meimei.meimusic.module.main.rankinglist.newmusic;

import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.http.ApiUtil;
import com.meimei.meimusic.http.api.Api;
import com.meimei.meimusic.module.main.callback.OnNewMusicListener;
import com.meimei.meimusic.utils.MusicUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicModel implements INewMusicModel{

    private Api mApi;

    public NewMusicModel() {
        mApi = ApiUtil.createApi(Api.class, ApiUtil.getBaseUrl());
    }


    @Override
    public void getNewMusicForNet(int size, final OnNewMusicListener listener) {
        mApi.getRankingList(MusicUtil.BILLBOARD_NEW_MUSIC, MusicUtil.OFFSET,size,MusicUtil.FIELDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RankingList>() {
                    @Override
                    public void accept(@NonNull RankingList list) throws Exception {
                        listener.succuss(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.error(throwable);
                    }
                });
    }
}
