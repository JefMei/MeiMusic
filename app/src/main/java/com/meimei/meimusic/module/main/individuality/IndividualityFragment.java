package com.meimei.meimusic.module.main.individuality;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseFragment;
import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.widget.CarouselView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/18.
 */
public class IndividualityFragment extends BaseFragment implements IIndividualityView{

    @BindView(R.id.linearlayout_views)
    LinearLayout mViewsLayout;
    @BindView(R.id.image_individuality_fm)
    ImageView mImageFm;
    @BindView(R.id.image_individuality_recommend)
    ImageView mImageRecommend;
    @BindView(R.id.image_individuality_hot_song)
    ImageView mImageHotSong;

    private final Integer[] imageRes = {R.mipmap.ic_vp_first,R.mipmap.ic_vp_second,R.mipmap.ic_vp_third,
            R.mipmap.ic_vp_fourth,R.mipmap.ic_vp_five,R.mipmap.ic_vp_six,R.mipmap.ic_vp_seven};

    private List<View> mViews = new ArrayList<>();

    private List<Individuality.Recommend_Item> recomSongList = new ArrayList<>();
    private List<Individuality.Radio_Item> radioList = new ArrayList<>();
    private List<Individuality.NewMusic_Item> newMusicList = new ArrayList<>();

    private IndividualityPresenter mPresenter;

    private RecomSongAdapter mRecomAdapter;
    private RadioAdapter mRadioAdapter;
    private NewMusicAdapter mNewMusicAdapter;

    private RecyclerView mRecomRecycView;
    private RecyclerView mNewMusicRecycView;
    private RecyclerView mRadioRecycView;

    private TextView mChangeTv;
    private CarouselView mVpView;
    private View mRecomView;
    private View mRadioView;
    private View mNewMusicView;
    private View mChangeView;

    private MusicConnection con;

    @Override
    protected void initView() {

        initOhter();
        initRecyclerView();
        initData();
        addView();
    }

    private void initOhter() {
        mPresenter = new IndividualityPresenter(this);
        mVpView = (CarouselView) getView().findViewById(R.id.carouseview_individuality);
        mChangeView = LayoutInflater.from(getActivity()).inflate(R.layout.include_change_individuality,null,false);
        mChangeTv = (TextView) mChangeView.findViewById(R.id.tv_change_individuality);


        /*mImageFm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.meimei.meimusic.service.MusicService.class);
                con = new MusicConnection();
                getActivity().bindService(intent, con, Context.BIND_AUTO_CREATE);
            }
        });

        mImageHotSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().unbindService(con);
            }
        });*/
    }

    private class MusicConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("tag","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("tag","onServiceDisconnected");
        }
    }

    private void initData() {
        mVpView.setData(Arrays.asList(imageRes));
        mPresenter.loadRecomSong();
        mPresenter.loadNewMusic();
        mPresenter.loadRadio();
    }

    private void initRecyclerView() {

        mRecomView = LayoutInflater.from(getActivity()).inflate(R.layout.view_recommend_song,null,false);
        mRadioView = LayoutInflater.from(getActivity()).inflate(R.layout.view_radio,null,false);
        mNewMusicView = LayoutInflater.from(getActivity()).inflate(R.layout.view_new_music,null,false);

        mRecomRecycView = (RecyclerView) mRecomView.findViewById(R.id.recyc_recommend_song);
        mNewMusicRecycView = (RecyclerView) mNewMusicView.findViewById(R.id.recyc_new_music);
        mRadioRecycView = (RecyclerView) mRadioView.findViewById(R.id.recyc_radio);

        mRecomRecycView.setNestedScrollingEnabled(false);
        mNewMusicRecycView.setNestedScrollingEnabled(false);
        mRadioRecycView.setNestedScrollingEnabled(false);

        mRecomAdapter = new RecomSongAdapter(getActivity());
        mNewMusicAdapter = new NewMusicAdapter(getActivity());
        mRadioAdapter = new RadioAdapter(getActivity());

        mRecomRecycView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecomRecycView.setAdapter(mRecomAdapter);
        mNewMusicRecycView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mNewMusicRecycView.setAdapter(mNewMusicAdapter);
        mRadioRecycView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRadioRecycView.setAdapter(mRadioAdapter);

    }

    private void addView() {
        mViewsLayout.addView(mRecomView);
        mViewsLayout.addView(mRadioView);
        mViewsLayout.addView(mNewMusicView);
        mViewsLayout.addView(mChangeView);
    }

    @Override
    public void requestRecomSongSuccess(List<Individuality.Recommend_Item> list) {
        recomSongList.addAll(list);
        mRecomAdapter.loadData(recomSongList);

    }

    @Override
    public void requestRecomSongError(String errorInfo) {

    }

    @Override
    public void requestRadioSuccess(List<Individuality.Radio_Item> list) {
        radioList.addAll(list);
        mRadioAdapter.loadData(radioList);
    }

    @Override
    public void requestRadioError(String errorInfo) {

    }

    @Override
    public void requestNewMusicSuccess(List<Individuality.NewMusic_Item> list) {
        newMusicList.addAll(list);
        mNewMusicAdapter.loadData(newMusicList);
    }

    @Override
    public void requestNewMusicError(String errorInfo) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_individuality;
    }

    public static IndividualityFragment newInstance(){
        IndividualityFragment individualityFragment = new IndividualityFragment();
        return individualityFragment;
    }
}
