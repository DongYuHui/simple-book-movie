package com.kyletung.simplebookmovie.adapter.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyletung.simplebookmovie.ui.search.SearchBookFragment;
import com.kyletung.simplebookmovie.ui.search.SearchMovieFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 21:24<br>
 * <br>
 * 搜索页面 Fragment 适配器
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {

    private SearchBookFragment mBookFragment;
    private SearchMovieFragment mMovieFragment;

    private static final String[] titles = {"书籍", "影视"};

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mBookFragment == null) mBookFragment = SearchBookFragment.newInstance();
                return mBookFragment;
            case 1:
                if (mMovieFragment == null) mMovieFragment = SearchMovieFragment.newInstance();
                return mMovieFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
