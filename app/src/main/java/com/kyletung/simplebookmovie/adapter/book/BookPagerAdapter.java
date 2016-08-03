package com.kyletung.simplebookmovie.adapter.book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyletung.simplebookmovie.ui.book.BookListFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 21:13<br>
 * <br>
 * FixMe
 */
public class BookPagerAdapter extends FragmentPagerAdapter {

    private BookListFragment mBookWish;
    private BookListFragment mBookRead;
    private BookListFragment mBookReading;

    private static final String[] titles = {"未读", "已读", "在读"};

    private String mUserId;

    public BookPagerAdapter(FragmentManager fm, String userId) {
        super(fm);
        mUserId = userId;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mBookWish == null) mBookWish = BookListFragment.newInstance(mUserId, "wish");
                return mBookWish;
            case 1:
                if (mBookRead == null) mBookRead = BookListFragment.newInstance(mUserId, "read");
                return mBookRead;
            case 2:
                if (mBookReading == null) mBookReading = BookListFragment.newInstance(mUserId, "reading");
                return mBookReading;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
