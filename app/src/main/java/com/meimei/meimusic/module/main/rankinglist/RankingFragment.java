package com.meimei.meimusic.module.main.rankinglist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.utils.LogUtil;

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

    //飙升榜
    public static final int BILLBOARD_NET_MUSIC = 25;
    //新歌榜
    public static final int BILLBOARD_NEW_MUSIC = 1;
    //原创音乐榜
    public static final int BILLBOARD_ORIGINAL = 200;
    //热歌榜
    public static final int BILLBOARD_HOT_MUSIC = 2;
    //ACG音乐榜
    public static final int BILLBOARD_KING = 100;
    //欧美金曲榜
    public static final int BILLBOARD_EU_UK = 21;
    //经典老哥榜
    public static final int BILLBOARD_CLASSIC_OLD = 22;

    private final String TAG = "RankingFragment";

    private int mOfficial[] = {BILLBOARD_NET_MUSIC,BILLBOARD_NEW_MUSIC,BILLBOARD_ORIGINAL
    ,BILLBOARD_HOT_MUSIC,BILLBOARD_KING};

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

        mOfficialView = LayoutInflater.from(getActivity()).inflate(R.layout.view_official_ranking,null,false);

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
            case BILLBOARD_NET_MUSIC:
                rankingMap.put(BILLBOARD_NET_MUSIC,List.song_list);
                break;
            case BILLBOARD_NEW_MUSIC:
                rankingMap.put(BILLBOARD_NEW_MUSIC,List.song_list);
                break;
            case BILLBOARD_ORIGINAL:
                rankingMap.put(BILLBOARD_ORIGINAL,List.song_list);
                break;
            case BILLBOARD_HOT_MUSIC:
                rankingMap.put(BILLBOARD_HOT_MUSIC,List.song_list);
                break;
            case BILLBOARD_KING:
                rankingMap.put(BILLBOARD_KING,List.song_list);
                break;
        }

        requestNum++;

        if (requestNum == 5){
            this.rankingList.addAll(rankingMap.get(BILLBOARD_NET_MUSIC));
            this.rankingList.addAll(rankingMap.get(BILLBOARD_NEW_MUSIC));
            this.rankingList.addAll(rankingMap.get(BILLBOARD_ORIGINAL));
            this.rankingList.addAll(rankingMap.get(BILLBOARD_HOT_MUSIC));
            this.rankingList.addAll(rankingMap.get(BILLBOARD_KING));
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
