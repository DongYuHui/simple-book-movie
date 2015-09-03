package com.kyletung.doubanbookmovie.home;


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
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init view pager and tab layout
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_home_viewpager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager()));
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.fragment_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


}
