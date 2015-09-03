package com.kyletung.doubanbookmovie.book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class BookPagerAdapter extends FragmentStatePagerAdapter {


    //get fragments contains unreaded, readed, reading
    BookFragmentUnreaded fragmentBookUnreaded;
    BookFragmentReaded fragmentBookReaded;
    BookFragmentReading fragmentBookReading;

    //titles
    String[] fragmentBookTabTitles = {"想看", "看过", "在看"};

    public BookPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentBookUnreaded = new BookFragmentUnreaded();
        fragmentBookReaded = new BookFragmentReaded();
        fragmentBookReading = new BookFragmentReading();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentBookTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentBookUnreaded;
        } else if (position == 1){
            return fragmentBookReaded;
        } else {
            return fragmentBookReading;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
