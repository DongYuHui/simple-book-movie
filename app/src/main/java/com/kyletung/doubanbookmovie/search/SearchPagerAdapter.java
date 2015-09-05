package com.kyletung.doubanbookmovie.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SearchPagerAdapter extends FragmentStatePagerAdapter {

    //init fragments contains book, movie
    SearchFragmentBook fragmentSearchBook;
    SearchFragmentMovie fragmentSearchMovie;

    //init pager title
    String[] fragmentSearchTabsTitles = {"书籍", "电影"};

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentSearchBook = new SearchFragmentBook();
        fragmentSearchMovie = new SearchFragmentMovie();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentSearchTabsTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentSearchBook;
        } else {
            return fragmentSearchMovie;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
