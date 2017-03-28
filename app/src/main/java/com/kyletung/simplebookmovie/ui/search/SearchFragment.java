package com.kyletung.simplebookmovie.ui.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.main.BaseFragment;
import com.kyletung.commonlib.utils.KeyboardUtil;
import com.kyletung.commonlib.utils.ToastUtil;
import com.kyletung.commonlib.view.android.BaseFrameLayout;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.view.spinner.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    //    @BindView(R.id.search_type)
//    TextView mSearchType;
    @BindView(R.id.search_type)
    NiceSpinner mSearchType;
    @BindView(R.id.search_icon)
    ImageView mSearchIcon;

    @BindView(R.id.search_recent)
    RecyclerView mSearchRecent;
    @BindView(R.id.layout_recent)
    FrameLayout mLayoutRecent;

    @BindView(R.id.search_result)
    RecyclerView mSearchResult;
    @BindView(R.id.layout_result)
    BaseFrameLayout mLayoutResult;

    private int mBookOrMovie = 0;    // 0 stands for book, 1 stands for movie

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
        // init search type
        List<String> searchTypeList = new LinkedList<>(Arrays.asList(getString(R.string.search_type_book), getString(R.string.search_type_movie)));
        mSearchType.attachDataSource(searchTypeList);
    }

    @Override
    protected void business(View view) {
        mSearchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBookOrMovie = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSearchInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showRecentSearch();
                } else {
                    hideRecentSearch();
                }
            }
        });
        mSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    clearSearchFocus();
                    startSearch(mSearchInput.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick({
            R.id.search_icon
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_icon:
                if (TextUtils.isEmpty(mSearchInput.getText())) {
                    ToastUtil.showToast(getActivity(), "Input Empty");
                } else {
                    clearSearchFocus();
                    startSearch(mSearchInput.getText().toString());
                }
                break;
        }
    }

    /**
     * 取消搜索框的焦点，隐藏软键盘
     */
    public void clearSearchFocus() {
        KeyboardUtil.hideKeyboard(getActivity(), mSearchInput);
        mSearchInput.clearFocus();
    }

    /**
     * 显示最近搜索的内容
     */
    private void showRecentSearch() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSearchIcon, "alpha", 0F, 1F);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mSearchIcon.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
        // TODO: 2017/3/24
        ToastUtil.showToast(getActivity(), "Show Recent");
    }

    /**
     * 隐藏最近搜索的内容
     */
    private void hideRecentSearch() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mSearchIcon, "alpha", 1F, 0F);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mSearchIcon.setVisibility(View.GONE);
            }
        });
        animator.start();
        // TODO: 2017/3/24
        ToastUtil.showToast(getActivity(), "Hide Recent");
    }

    /**
     * 搜索内容
     *
     * @param content 内容
     */
    private void startSearch(String content) {
        hideRecentSearch();
        mLayoutResult.startLoad();
        if (mBookOrMovie == 0) {
            // TODO: 2017/3/28 start search book
            mLayoutResult.postDelayed(() -> {
                mLayoutResult.stopLoad();
                ToastUtil.showToast(getActivity(), "Search Book " + content);
            }, 3000);
        } else if (mBookOrMovie == 1) {
            // TODO: 2017/3/28 start search movie
            mLayoutResult.postDelayed(() -> {
                mLayoutResult.stopLoad();
                ToastUtil.showToast(getActivity(), "Search Movie " + content);
            }, 3000);
        } else {
            mLayoutResult.stopLoad();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        clearSearchFocus();
    }

}
