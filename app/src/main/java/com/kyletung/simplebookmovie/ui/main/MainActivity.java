package com.kyletung.simplebookmovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.commonlib.utils.KeyboardUtil;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.main.TabPagerAdapter;
import com.kyletung.simplebookmovie.view.SwitchLayout;

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

    @BindView(R.id.switch_container)
    SwitchLayout mSwitchContainer;
    //    @BindView(R.id.cover_view)
//    CoverView mCoverView;
    @BindView(R.id.main_content)
    ViewPager mTabViewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mNavigationBar;

    @BindView(R.id.search_input)
    EditText mSearchInput;
    @BindView(R.id.search_recent)
    RecyclerView mSearchRecent;
    @BindView(R.id.search_result)
    RecyclerView mSearchResult;

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
        mSwitchContainer.setOnPageChangeListener(new SwitchLayout.OnPageChangeListener() {
            @Override
            public void onChange(SwitchLayout.Type type) {
                switch (type) {
                    case LEFT:
                        break;
                    case RIGHT:
                        // TODO: 2017/3/23 隐藏输入框，清除搜索框焦点
                        KeyboardUtil.hideKeyboard(MainActivity.this, mSearchInput);
                        mSwitchContainer.requestFocus(); // 清除搜索框焦点 通过其他控件请求焦点 来实现
                        break;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSwitchContainer.requestFocus();    // 清除搜索框的焦点
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (!mSwitchContainer.isPageInRight()) {
                    mSwitchContainer.scrollToRight();
                } else {
                    if (mTabViewPager.getCurrentItem() != 0) {
                        mNavigationBar.selectTab(0, true);
                    } else {
                        finish();
                    }
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
