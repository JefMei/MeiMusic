package com.meimei.meimusic.module.main.rankinglist.ranking;

import com.meimei.meimusic.base.presenter.BasePresenter;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.module.main.callback.OnRankingListener;

/**
 * Created by 梅梅 on 2017/4/15.
 */
public class RankingPresenter extends BasePresenter{

    private IRankingView mView;
    private IRankingModel mModel;

    public RankingPresenter(IRankingView iRankingView) {
        super(iRankingView);
        mView = iRankingView;
        mModel = new RankingModel();
    }

    public void loadOfficial(int type,int offset,int size){
        mModel.requestRankingListForNet(type, offset, size, new OnRankingListener() {
            @Override
            public void succees(RankingList rankingList) {
                mView.loadOfficialSuccess(rankingList);
            }

            @Override
            public void error(Throwable throwable) {
                mView.loadOfficialError(throwable.toString());
            }
        });
    }

}
