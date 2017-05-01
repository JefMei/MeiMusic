package com.meimei.meimusic.module.main.rankinglist.newmusic;

import com.meimei.meimusic.MyApplication;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.module.main.callback.OnNewMusicListener;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.NetWorkUtils;

import java.util.List;

/**
 * Created by 梅梅 on 2017/4/18.
 */
public class NewMusicPresenter {

    private final String TAG = "NewMusicPresenter";
    private INewMusicModel mModel;
    private INewMusicView mView;

    private List<Long> songIds;

    public NewMusicPresenter(INewMusicView view) {
        mView = view;
        mModel = new NewMusicModel();
    }

    public void requestNewMusicForNet(int size){

        if (NetWorkUtils.isNetworkConnected(MyApplication.getContext())){
            mView.showLoading();
        }else {
            mView.showNetError();
        }

        mModel.getNewMusicForNet(size,onNewMusicListener);

    }

    private OnNewMusicListener onNewMusicListener = new OnNewMusicListener() {
        @Override
        public void succuss(RankingList rankingList) {

            mView.hideLoading();
            mView.showNewMusicList(rankingList.song_list);
            LogUtil.d(TAG,"request NewMusicData succuss");
        }

        @Override
        public void error(Throwable throwable) {
            LogUtil.d(TAG,"request NewMusicData error:" + throwable.toString());
        }
    };

}
