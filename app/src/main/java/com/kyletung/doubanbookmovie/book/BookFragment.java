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
        viewPager.setAdapter(new BookPagerAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_book_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //fix tablayout no title bug
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }


}
