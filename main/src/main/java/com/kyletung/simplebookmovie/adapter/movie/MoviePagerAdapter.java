package com.kyletung.simplebookmovie.adapter.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyletung.simplebookmovie.ui.movie.MovieBoardFragment;
import com.kyletung.simplebookmovie.ui.movie.MovieTopFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 16:35<br>
 * <br>
 * 电影主页适配器
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {

    // Fragments
    private MovieTopFragment mMovieTop;
    private MovieBoardFragment mMovieBoard;

    // Titles
    private String[] titles = {"北美票房榜", "Top 250"};

    public MoviePagerAdapter(FragmentManager fm) {
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
                if (mMovieBoard == null) {
                    mMovieBoard = MovieBoardFragment.newInstance();
                }
                return mMovieBoard;
            case 1:
                if (mMovieTop == null) {
                    mMovieTop = MovieTopFragment.newInstance();
                }
                return mMovieTop;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
