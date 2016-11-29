package com.kyletung.simplebookmovie.ui.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.main.TabPagerAdapter;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.ui.about.AboutActivity;
import com.kyletung.simplebookmovie.ui.search.SearchActivity;
import com.kyletung.simplebookmovie.ui.settings.SettingsActivity;
import com.kyletung.simplebookmovie.view.TabViewPager;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/27 at 12:00<br>
 * <br>
 * 主页 Activity
 */
public class MainActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private TabViewPager mTabViewPager;
    private TabPagerAdapter mTabAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        // init toolbar
        setToolbar(getString(R.string.app_name), false);
        // temp
        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        // init ViewPager
        mTabViewPager = (TabViewPager) findViewById(R.id.main_content);
        mTabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mTabViewPager.setAdapter(mTabAdapter);
        // init bottom bar
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_movie, getString(R.string.main_tab_movie)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_book, getString(R.string.main_tab_book)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_user, getString(R.string.main_tab_user)))
                .initialise();
        bottomNavigationBar.setAutoHideEnabled(true);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position) {
                mTabViewPager.setCurrentItem(position, false);
                mTabAdapter.setTabLayout(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }

        });
        // init first page
        mTabLayout.post(() -> {
            mTabViewPager.setCurrentItem(0, false);
            mTabAdapter.setTabLayout(0);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_search:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                intentSearch.putExtra("content", "");
                startActivity(intentSearch);
                break;
            case R.id.main_menu_settings:
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.main_menu_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
        }
        return true;
    }

    /**
     * 获取当前页面 TabLayout
     *
     * @return 当前页面 TabLayout
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }

}
