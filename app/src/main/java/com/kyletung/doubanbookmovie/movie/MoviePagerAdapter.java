package com.kyletung.doubanbookmovie.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MoviePagerAdapter extends FragmentStatePagerAdapter {


    //get fragments contains unwatched, watched, watching
    MovieFragmentTop250 fragmentMovieTop250;
    MovieFragmentAmerica fragmentMovieAmerica;

    //titles
    String[] fragmentMovieTabTitles = {"北美票房榜", "Top250"};

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMovieTop250 = new MovieFragmentTop250();
        fragmentMovieAmerica = new MovieFragmentAmerica();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentMovieTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentMovieAmerica;
        } else {
            return fragmentMovieTop250;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
