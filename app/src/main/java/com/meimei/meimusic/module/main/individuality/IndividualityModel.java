package com.meimei.meimusic.module.main.individuality;

import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.http.ApiUtil;
import com.meimei.meimusic.http.api.Api;
import com.meimei.meimusic.module.main.callback.OnIndividualityListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class IndividualityModel implements IIndividualityModel {

    private Api mApi;

    public IndividualityModel() {
        mApi = ApiUtil.createApi(Api.class,ApiUtil.getBaseUrl());

    }

    @Override
    public void requestIndividualityForNet(final OnIndividualityListener listener) {
        mApi.getIndividuality()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Individuality>() {
                    @Override
                    public void accept(@NonNull Individuality individuality) throws Exception {
                        listener.success(individuality);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        listener.error(throwable);
                    }
                });
    }

}