package com.kyletung.simplebookmovie.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyletung.simplebookmovie.ui.book.BookFragment;
import com.kyletung.simplebookmovie.ui.movie.MovieFragment;
import com.kyletung.simplebookmovie.ui.user.UserFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/30 at 23:07<br>
 * <br>
 * 主页的 Fragment 适配器
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    private UserFragment mFragmentMine;
    private BookFragment mFragmentBook;
    private MovieFragment mFragmentMovie;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mFragmentMovie == null) mFragmentMovie = MovieFragment.newInstance();
                return mFragmentMovie;
            case 1:
                if (mFragmentBook == null) mFragmentBook = BookFragment.newInstance();
                return mFragmentBook;
            case 2:
                if (mFragmentMine == null) mFragmentMine = UserFragment.newInstance();
                return mFragmentMine;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
