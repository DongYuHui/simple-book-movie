package com.kyletung.simplebookmovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.main.TabPagerAdapter;
import com.kyletung.simplebookmovie.ui.about.AboutActivity;
import com.kyletung.simplebookmovie.ui.search.SearchActivity;
import com.kyletung.simplebookmovie.ui.settings.SettingsActivity;

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

    @BindView(R.id.main_tab)
    TabLayout mTabLayout;
    @BindView(R.id.main_content)
    ViewPager mTabViewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mNavigationBar;

    private TabPagerAdapter mTabAdapter;

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
        // init toolbar
        setToolbar(getString(R.string.app_name), false);
        // init ViewPager
        mTabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mTabViewPager.setAdapter(mTabAdapter);
        // init bottom bar
        mNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_movie, getString(R.string.main_tab_movie)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_book, getString(R.string.main_tab_book)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_tab_user, getString(R.string.main_tab_user)))
                .initialise();
        mNavigationBar.setAutoHideEnabled(true);
    }

    @Override
    protected void business() {
        mNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

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
                SettingsActivity.start(MainActivity.this);
                break;
            case R.id.main_menu_about:
                AboutActivity.start(MainActivity.this);
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
