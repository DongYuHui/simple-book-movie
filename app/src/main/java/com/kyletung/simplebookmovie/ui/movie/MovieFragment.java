package com.kyletung.simplebookmovie.ui.movie;

import android.os.Bundle;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MoviePagerAdapter;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
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

    private TabViewPager mViewPager;

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
        mViewPager = (TabViewPager) view.findViewById(R.id.movie_viewpager);
        mViewPager.setSwipeEnabled(true);
        MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
    }

    /**
     * 设置外部 Activity 的 TabLayout
     */
    public void setTabLayout() {
        ((MainActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).getTabLayout().setupWithViewPager(mViewPager);
    }

}
