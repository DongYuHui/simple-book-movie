package com.kyletung.simplebookmovie.ui.book;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookPagerAdapter;
import com.kyletung.simplebookmovie.ui.BaseFragment;
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

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_book;
    }

    @Override
    protected void init(View view) {
        // init content views
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.book_tab);
        TabViewPager viewPager = (TabViewPager) view.findViewById(R.id.book_viewpager);
        viewPager.setSwipeEnabled(true);
        BookPagerAdapter adapter = new BookPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
