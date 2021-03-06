package com.meimei.meimusic.module.main.individuality;

import com.meimei.meimusic.base.presenter.BasePresenter;
import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.module.main.callback.OnIndividualityListener;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public class IndividualityPresenter extends BasePresenter {

    private IIndividualityView mView;
    private IIndividualityModel mModel;

    public IndividualityPresenter(IIndividualityView view) {
        super(view);
        mView = view;
        mModel = new IndividualityModel();
    }

    public void loadRecomSong(){
        mModel.requestIndividualityForNet(new OnIndividualityListener() {
            @Override
            public void success(Individuality individuality) {
                mView.requestRecomSongSuccess(individuality.result.diy.result);
            }

            @Override
            public void error(Throwable throwable) {
                mView.requestRecomSongError(throwable.toString());
            }
        });
    }

    public void loadRadio(){
        mModel.requestIndividualityForNet(new OnIndividualityListener() {
            @Override
            public void success(Individuality individuality) {
                mView.requestRadioSuccess(individuality.result.radio.result);
            }

            @Override
            public void error(Throwable throwable) {
                mView.requestRadioError(throwable.toString());
            }
        });
    }

    public void loadNewMusic(){
        mModel.requestIndividualityForNet(new OnIndividualityListener() {
            @Override
            public void success(Individuality individuality) {
                mView.requestNewMusicSuccess(individuality.result.mix_1.result);
            }

            @Override
            public void error(Throwable throwable) {
                mView.requestNewMusicError(throwable.toString());
            }
        });
    }

    public void loadExclusive(){
        mModel.requestIndividualityForNet(new OnIndividualityListener() {
            @Override
            public void success(Individuality individuality) {

            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }
}
