package com.kyletung.simplebookmovie.ui.settings;

import android.os.Bundle;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.ui.BaseDialogFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 15:53<br>
 * <br>
 * FixMe
 */
public class AboutFragment extends BaseDialogFragment {

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView(View view) {

    }

}
