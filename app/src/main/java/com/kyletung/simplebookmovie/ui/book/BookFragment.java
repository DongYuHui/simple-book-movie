package com.kyletung.simplebookmovie.ui.book;

import android.view.View;

import com.kyletung.commonlib.main.BaseFragment;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookPagerAdapter;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
import com.kyletung.simplebookmovie.view.TabViewPager;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 21:07<br>
 * <br>
 * 书籍页面
 */
public class BookFragment extends BaseFragment {

    private TabViewPager mViewPager;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_book;
    }

    @Override
    protected void initView(View view) {
        // init content views
        mViewPager = (TabViewPager) view.findViewById(R.id.book_viewpager);
        mViewPager.setSwipeEnabled(true);
        BookPagerAdapter adapter = new BookPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void business(View view) {
    }

    /**
     * 设置外部 Activity 的 TabLayout
     */
    public void setTabLayout() {
        ((MainActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).getTabLayout().setupWithViewPager(mViewPager);
    }

}
