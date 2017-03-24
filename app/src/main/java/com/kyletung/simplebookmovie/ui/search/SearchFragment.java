package com.kyletung.simplebookmovie.ui.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.kyletung.commonlib.main.BaseFragment;
import com.kyletung.commonlib.utils.KeyboardUtil;
import com.kyletung.simplebookmovie.R;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/3/24<br>
 * <br>
 * FixMe
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.search_input)
    EditText mSearchInput;
    @BindView(R.id.search_recent)
    RecyclerView mSearchRecent;
    @BindView(R.id.search_result)
    RecyclerView mSearchResult;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void business(View view) {

    }

    public void clearSearchFocus() {
        mSearchInput.clearFocus();
        KeyboardUtil.hideKeyboard(getActivity(), mSearchInput);
    }

    @Override
    public void onPause() {
        super.onPause();
        clearSearchFocus();
    }

}
