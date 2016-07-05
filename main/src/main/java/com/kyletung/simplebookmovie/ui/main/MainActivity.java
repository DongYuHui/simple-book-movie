package com.kyletung.simplebookmovie.ui.main;

import android.view.View;
import android.widget.TextView;

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
public class MainActivity extends BaseActivity implements View.OnClickListener {

    // Tabs and Statement
    private int mTabPosition = -1;
    private TabViewPager mTabViewPager;

    // temp
    private TextView mTabBook;
    private TextView mTabMine;
    private TextView mTabMovie;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        // init ViewPager
        mTabViewPager = (TabViewPager) findViewById(R.id.main_content);
        TabPagerAdapter tabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mTabViewPager.setAdapter(tabAdapter);
        // init tab
        mTabBook = (TextView) findViewById(R.id.tab_book);
        mTabMine = (TextView) findViewById(R.id.tab_mine);
        mTabMovie = (TextView) findViewById(R.id.tab_movie);
        // set listener
        setListener();
    }

    private void setListener() {
        mTabBook.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
        mTabMovie.setOnClickListener(this);
    }

    private void setTabMovie() {

    }

    private void setTabBook() {

    }

    private void setTabMine() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_book:
                mTabViewPager.setCurrentItem(1);
                break;
            case R.id.tab_mine:
                mTabViewPager.setCurrentItem(2);
                break;
            case R.id.tab_movie:
                mTabViewPager.setCurrentItem(0);
                break;
        }
    }

}
