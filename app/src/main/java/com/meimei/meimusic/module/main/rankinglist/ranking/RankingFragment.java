package com.meimei.meimusic.module.main.rankinglist.ranking;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.utils.LogUtil;
import com.meimei.meimusic.utils.MusicUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class RankingFragment extends BaseFragment implements IRankingView{

    @BindView(R.id.linearlayout_views)
    LinearLayout mViewsLayout;

    private final String TAG = "RankingFragment";

    private int mOfficial[] = {MusicUtil.BILLBOARD_NET_MUSIC, MusicUtil.BILLBOARD_NEW_MUSIC, MusicUtil.BILLBOARD_ORIGINAL
    , MusicUtil.BILLBOARD_HOT_MUSIC, MusicUtil.BILLBOARD_KING};

    private int requestNum = 0; //记录请求次数,到5次就说明已经请求完一轮了

    private RankingPresenter mPresenter;

    private List<RankingList.songList> rankingList = new ArrayList<>();
    private HashMap<Integer,List<RankingList.songList>> rankingMap = new HashMap<>();

    private RankingAdapter mOfficialAdapter;

    private RecyclerView mOfficialRecyc;

    private View mOfficialView;

    @Override
    protected void initView() {
        initOther();
        initRecyclerView();
        initData();
        addView();
    }
    private void initOther() {
        mPresenter = new RankingPresenter(this);

        mOfficialView = LayoutInflater.from(getActivity()).inflate(R.layout.view_ranking_official,null,false);

    }

    private void initRecyclerView() {

        mOfficialRecyc = (RecyclerView) mOfficialView.findViewById(R.id.recyc_ranking_official);
        mOfficialRecyc.setNestedScrollingEnabled(false);
        mOfficialAdapter = new RankingAdapter(getActivity());
        mOfficialRecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOfficialRecyc.setAdapter(mOfficialAdapter);

    }

    private void initData() {
        for (int i=0;i < 5; i++){
            mPresenter.loadOfficial(mOfficial[i],0,3);
        }
    }

    private void addView() {
        mViewsLayout.addView(mOfficialView);
    }


    @Override
    public void loadOfficialSuccess(RankingList List) {
        if (sortData(List)){
            mOfficialAdapter.loadData(rankingList);
        }
    }

    @Override
    public void loadOfficialError(String error) {
        LogUtil.i(TAG,error);
    }

    private boolean sortData(RankingList List){

        switch (List.billboard.billboard_type){
            case MusicUtil.BILLBOARD_NET_MUSIC:
                rankingMap.put(MusicUtil.BILLBOARD_NET_MUSIC,List.song_list);
                break;
            case MusicUtil.BILLBOARD_NEW_MUSIC:
                rankingMap.put(MusicUtil.BILLBOARD_NEW_MUSIC,List.song_list);
                break;
            case MusicUtil.BILLBOARD_ORIGINAL:
                rankingMap.put(MusicUtil.BILLBOARD_ORIGINAL,List.song_list);
                break;
            case MusicUtil.BILLBOARD_HOT_MUSIC:
                rankingMap.put(MusicUtil.BILLBOARD_HOT_MUSIC,List.song_list);
                break;
            case MusicUtil.BILLBOARD_KING:
                rankingMap.put(MusicUtil.BILLBOARD_KING,List.song_list);
                break;
        }

        requestNum++;

        if (requestNum == 5){
            this.rankingList.addAll(rankingMap.get(MusicUtil.BILLBOARD_NET_MUSIC));
            this.rankingList.addAll(rankingMap.get(MusicUtil.BILLBOARD_NEW_MUSIC));
            this.rankingList.addAll(rankingMap.get(MusicUtil.BILLBOARD_ORIGINAL));
            this.rankingList.addAll(rankingMap.get(MusicUtil.BILLBOARD_HOT_MUSIC));
            this.rankingList.addAll(rankingMap.get(MusicUtil.BILLBOARD_KING));
            requestNum = 0;
            return true;
        }

        return false;

    }



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_ranking;
    }

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment = new RankingFragment();
        return rankingFragment;
    }

}
