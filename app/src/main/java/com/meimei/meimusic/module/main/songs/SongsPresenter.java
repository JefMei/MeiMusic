package com.meimei.meimusic.module.main.songs;

import com.meimei.meimusic.base.presenter.BasePresenter;
import com.meimei.meimusic.entity.SongList;
import com.meimei.meimusic.module.main.callback.OnSongsListener;

/**
 * Created by 梅梅 on 2017/4/9.
 */
public class SongsPresenter extends BasePresenter<ISongsView>{

    private ISongsView mView;
    private ISongsModel mModel;

    public SongsPresenter(ISongsView ISongsView) {
        super(ISongsView);
        mView = ISongsView;
        mModel = new SongsModel();
    }

    public void loadSongs(int pageSize,int pageNo){
        mModel.getSongsForNet(new OnSongsListener() {
            @Override
            public void succes(SongList songList) {
                mView.requestSongsSucces(songList.content);
            }

            @Override
            public void error(Throwable throwable) {
                mView.requestSongsError(throwable.toString());
            }
        },pageSize,pageNo);
    }

    public void loadMoreSongs(int pageSize,int pageNo){
        mModel.getSongsForNet(new OnSongsListener() {
            @Override
            public void succes(SongList songList) {
                mView.requestSongsSucces(songList.content);
            }

            @Override
            public void error(Throwable throwable) {
                mView.requestSongsError(throwable.toString());
            }
        },pageSize,pageNo);
    }

}
