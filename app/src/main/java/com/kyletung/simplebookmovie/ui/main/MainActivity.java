package com.kyletung.simplebookmovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.main.TabPagerAdapter;

import butterknife.BindView;

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

//    @BindView(R.id.cover_view)
//    CoverView mCoverView;
    @BindView(R.id.main_content)
    ViewPager mTabViewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mNavigationBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(starter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // init ViewPager
        TabPagerAdapter tabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mTabViewPager.setAdapter(tabAdapter);
        mTabViewPager.setOffscreenPageLimit(2);
        // init bottom bar
        mNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_movie, getString(R.string.main_tab_movie)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_book, getString(R.string.main_tab_book)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_user, getString(R.string.main_tab_user)))
                .initialise();
        mNavigationBar.setAutoHideEnabled(true);
        // init cover
//        mCoverView.setImage(R.mipmap.launcher_icon);
    }

    @Override
    protected void business() {
        mNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position) {
                mTabViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }

        });
//        mCoverView.setOnSlideListener(() -> {
//            mCoverView.setVisibility(View.GONE);
//            ((ViewGroup) findViewById(android.R.id.content)).removeView(mCoverView);
//            mCoverView = null;
//        });
    }

}
