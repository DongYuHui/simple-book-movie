package com.kyletung.simplebookmovie.ui.movie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MoviePagerAdapter;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.view.TabViewPager;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 16:08<br>
 * <br>
 * 电影主页面 Fragment
 */
public class MovieFragment extends BaseFragment {

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void init(View view) {
        // init view
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.movie_tab);
        TabViewPager viewPager = (TabViewPager) view.findViewById(R.id.movie_viewpager);
        viewPager.setSwipeEnabled(true);
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
