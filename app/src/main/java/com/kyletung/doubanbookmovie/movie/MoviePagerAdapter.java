package com.kyletung.doubanbookmovie.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MoviePagerAdapter extends FragmentStatePagerAdapter {


    //get fragments contains unwatched, watched, watching
    MovieFragmentUnwatched fragmentMovieUnwatched;
    MovieFragmentWatched fragmentMovieWatched;
    MovieFragmentWatching fragmentMovieWatching;


    //titles
    String[] fragmentMovieTabTitles = {"想看", "看过", "在看"};

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMovieUnwatched = new MovieFragmentUnwatched();
        fragmentMovieWatched = new MovieFragmentWatched();
        fragmentMovieWatching = new MovieFragmentWatching();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentMovieTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentMovieUnwatched;
        } else if (position == 1){
            return fragmentMovieWatched;
        } else {
            return fragmentMovieWatching;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
