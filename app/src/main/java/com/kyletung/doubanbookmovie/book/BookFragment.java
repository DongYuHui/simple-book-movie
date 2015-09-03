package com.kyletung.doubanbookmovie.book;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.doubanbookmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {


    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        //init view pager and tab layout
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_book_viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new BookPagerAdapter(getFragmentManager()));
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.fragment_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


}
