package com.meimei.meimusic.module;

import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.meimei.meimusic.R;
import com.meimei.meimusic.base.view.BaseActivity;

import butterknife.BindView;

/**
 * Created by 梅梅 on 2017/3/13.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    @Override
    protected void initFragment() {

    }

    @Override
    protected void initView() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);    //取消toolbar的title


        mNavView.setItemIconTintList(null);
        ColorStateList colorStateList = getResources().getColorStateList(R.color.nav_text);
        mNavView.setItemTextColor(colorStateList);
        mNavView.setItemTextAppearance(R.style.nav_text);


//        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_main);

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

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

}
