package com.kyletung.simplebookmovie.ui;

import android.os.Bundle;
import android.view.View;

import com.kyletung.simplebookmovie.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/30 at 22:24<br>
 * <br>
 * TestFragment
 */
public class TestFragment extends BaseFragment {

    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_test;
    }

    @Override
    protected void init(View view) {

    }

}
