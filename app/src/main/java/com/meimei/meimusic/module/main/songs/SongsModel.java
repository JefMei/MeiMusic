package com.meimei.meimusic.module.main.songs;

import com.meimei.meimusic.entity.SongList;
import com.meimei.meimusic.http.ApiUtil;
import com.meimei.meimusic.http.api.Api;
import com.meimei.meimusic.module.main.callback.OnSongsListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public class SongsModel implements ISongsModel{

    private Api mApi;

    public SongsModel() {
        mApi = ApiUtil.createApi(Api.class,ApiUtil.getBaseUrl());
    }

    @Override
    public void getSongsForNet(final OnSongsListener listener, int pageSize, int pageNo) {
        mApi.getSongs(pageSize,pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SongList>() {
                    @Override
                    public void accept(@NonNull SongList songList) throws Exception {
                        listener.succes(songList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.error(throwable);
                    }
                });
    }
}
