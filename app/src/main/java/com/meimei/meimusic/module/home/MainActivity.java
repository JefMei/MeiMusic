package com.meimei.meimusic.module.home;

import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;
import com.meimei.meimusic.module.main.MainFragment;
import com.meimei.meimusic.module.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.viewpager_main)
    ViewPager mViewPager;
    @BindView(R.id.drawerlayout_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.image_drawer)
    ImageView mImageDrawer;
    @BindView(R.id.image_main)
    ImageView mImageMain;
    @BindView(R.id.image_mine)
    ImageView mImageMine;
    @BindView(R.id.image_friends)
    ImageView mImageFriends;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<ImageView> mTabs = new ArrayList<>();

    private MainFragment mMainFragment;

    private MineFragment mMineFragment;

    @Override
    protected void initFragment() {

        mMainFragment = MainFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        mFragments.add(mMainFragment);
        mFragments.add(mMineFragment);

        mTabs.add(mImageMain);
        mTabs.add(mImageMine);
        mTabs.add(mImageFriends);

    }

    @Override
    protected void initView() {

        initToolbar();
        initNavigationView();
        initViewPager();
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);    //取消toolbar的title
    }

    private void initNavigationView(){
        mNavView.setItemIconTintList(null);
        ColorStateList colorStateList = getResources().getColorStateList(R.color.nav_text);
        mNavView.setItemTextColor(colorStateList);
        mNavView.setItemTextAppearance(R.style.nav_text);

//        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_main);
    }

    private void initViewPager(){
        mImageMain.setSelected(true);
        HomePagerAdapter mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mAdapter.setFragments(mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main,menu);
        setSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setSearchView(Menu menu) {

        MenuItem item = menu.findItem(R.id.toolbar_main_search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        if (mSearchView != null){
            mSearchView.setQueryHint(getResources().getString(R.string.search_hint));
            ((EditText)mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                    .setHintTextColor(getResources().getColor(R.color.search_hint));
        }

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
    }

    private void swichTab(int position){
        for (int i=0;i<mTabs.size();i++){
            if (i != position){
                mTabs.get(i).setSelected(false);
            }else {
                mTabs.get(i).setSelected(true);
            }
        }
    }

    @OnClick(R.id.image_drawer)
    void doDrawer(){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.image_main)
    void doMain(){
        mViewPager.setCurrentItem(0);

    }

    @OnClick(R.id.image_mine)
    void doMine(){
        mViewPager.setCurrentItem(1);
    }

    @OnClick(R.id.image_friends)
    void doFriends(){
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        swichTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
