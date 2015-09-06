package com.kyletung.doubanbookmovie.book;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {

    //init viewpager and tablayout
    ViewPager viewPager;
    TabLayout tabLayout;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        //init view pager and tab layout
        viewPager = (ViewPager) view.findViewById(R.id.fragment_book_viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new BookPagerAdapter(getFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_book_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //fix tablayout no title bug
        if (ViewCompat.isLaidOut(tabLayout)) {
            tabLayout.setupWithViewPager(viewPager);
        } else {
            tabLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    tabLayout.setupWithViewPager(viewPager);
                    tabLayout.removeOnLayoutChangeListener(this);
                }
            });
        }

        return view;
    }


}
