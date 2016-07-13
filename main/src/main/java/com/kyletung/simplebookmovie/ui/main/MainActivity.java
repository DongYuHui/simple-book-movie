package com.kyletung.simplebookmovie.ui.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.main.TabPagerAdapter;
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

    // Search Bar
    private int mSearchBarHeight;
    private FrameLayout mSearchBar;

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
                if (position == 2) {
                    hideSearchBar();
                } else {
                    showSearchBar();
                }
                return true;
            }
        });
        // init search bar
        mSearchBar = (FrameLayout) findViewById(R.id.search_bar);
    }

    private void hideSearchBar() {
        if (mSearchBar.getVisibility() == View.VISIBLE) {
            mSearchBarHeight = mSearchBar.getMeasuredHeight();
            ObjectAnimator alpha = ObjectAnimator.ofFloat(mSearchBar, "alpha", 1.0f, 0f);
            ObjectAnimator translation = ObjectAnimator.ofFloat(mSearchBar, "translationY", 0f, -mSearchBarHeight);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(alpha, translation);
            set.setDuration(250);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSearchBar.setVisibility(View.GONE);
                }
            });
            set.start();
        }
    }

    private void showSearchBar() {
        if (mSearchBar.getVisibility() == View.GONE) {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(mSearchBar, "alpha", 0f, 1.0f);
            ObjectAnimator translation = ObjectAnimator.ofFloat(mSearchBar, "translationY", -mSearchBarHeight, 0f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(alpha, translation);
            set.setDuration(250);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mSearchBar.setVisibility(View.VISIBLE);
                }
            });
            set.start();
        }
    }

}
