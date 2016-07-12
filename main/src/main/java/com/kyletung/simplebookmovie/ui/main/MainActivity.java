package com.kyletung.simplebookmovie.ui.main;

import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.TabPagerAdapter;
import com.kyletung.simplebookmovie.ui.BaseActivity;
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

    // Tabs and Statement
    private TabViewPager mTabViewPager;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        // init data
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        // init ViewPager
        mTabViewPager = (TabViewPager) findViewById(R.id.main_content);
        TabPagerAdapter tabAdapter = new TabPagerAdapter(getSupportFragmentManager(), userId);
        mTabViewPager.setAdapter(tabAdapter);
        // init bottom bar
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomNavigation.setForceTint(true);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this, R.menu.main_tab);
        adapter.setupWithBottomNavigation(bottomNavigation);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mTabViewPager.setCurrentItem(position);
                return true;
            }
        });
    }

}
