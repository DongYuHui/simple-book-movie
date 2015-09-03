package com.kyletung.doubanbookmovie.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    //get fragments contains showing, willshow
    HomeFragmentShowing fragmentHomeShowing;
    HomeFragmentWillshow fragmentHomeWillshow;

    //titles
    String[] fragmentHomeTabTitles = {"正在热映", "即将上映"};

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentHomeShowing = new HomeFragmentShowing();
        fragmentHomeWillshow = new HomeFragmentWillshow();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentHomeTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return fragmentHomeShowing;
        } else {
            return fragmentHomeWillshow;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
