package com.kyletung.doubanbookmovie.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MoviePagerAdapter extends FragmentStatePagerAdapter {


    //get fragments contains unwatched, watched, watching
    MovieFragmentTop250 fragmentMovieTop250;
    MovieFragmentAmerica fragmentMovieAmerica;
    MovieFragmentNews fragmentMovieNews;


    //titles
    String[] fragmentMovieTabTitles = {"Top250", "新片榜", "北美票房榜"};

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMovieTop250 = new MovieFragmentTop250();
        fragmentMovieAmerica = new MovieFragmentAmerica();
        fragmentMovieNews = new MovieFragmentNews();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentMovieTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentMovieTop250;
        } else if (position == 1){
            return fragmentMovieNews;
        } else {
            return fragmentMovieAmerica;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
