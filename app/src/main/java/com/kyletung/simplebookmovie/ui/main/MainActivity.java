package com.kyletung.simplebookmovie.ui.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
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
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setForceTint(true);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this, R.menu.main_tab);
        adapter.setupWithBottomNavigation(bottomNavigation);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mTabViewPager.setCurrentItem(position, false);
                mTabAdapter.setTabLayout(position);
                return true;
            }
        });
        // init first page
        mTabViewPager.setCurrentItem(0, false);
        mTabAdapter.setTabLayout(0);
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
