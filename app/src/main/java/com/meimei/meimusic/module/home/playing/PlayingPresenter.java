package com.meimei.meimusic.module.home.playing;

import com.meimei.meimusic.base.presenter.BasePresenter;
import com.meimei.meimusic.utils.MusicUtil;
import com.meimei.meimusic.utils.PrefrencesManager;
import com.meimei.meimusic.utils.TimeUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 梅梅 on 2017/5/6.
 */
public class PlayingPresenter extends BasePresenter<IPlayingView>{

    private final String TAG = "PlayingPresenter";

    private Disposable disposable;

    public PlayingPresenter(IPlayingView baseView) {
        super(baseView);
    }

    public void updateSeekbar(){

        int played;
        int duration;
        if (MusicUtil.isPlaying()){
            played = MusicUtil.getCurrentPosition();
            duration = MusicUtil.getDuration();
        }else {
            played = PrefrencesManager.getInstance().getInt(PrefrencesManager.PLAYEDPOSITION,0);
            duration = PrefrencesManager.getInstance().getInt(PrefrencesManager.SONGDURATION,0);
        }


        getView().setFirstSeekbar();
        getView().setPlayedTime(TimeUtil.formatTime(played));
        getView().setDurationTime(TimeUtil.formatTime(duration));

        disposable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (MusicUtil.isPlaying()){
                            getView().setSeekbar();
                            getView().setPlayedTime(TimeUtil.formatTime(MusicUtil.getCurrentPosition()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        getView().updateSeekbarFailure();
                    }
                });
    }

    public void release(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void setEffects(){
        String big_pic = PrefrencesManager.getInstance().getString(PrefrencesManager.BIGPICURL,"");
        getView().setBlurBg(big_pic);

    }

}
