package com.kyletung.simplebookmovie.ui.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.load.SwipeToLoadLayout;
import com.kyletung.commonlib.main.BaseLoadFragment;
import com.kyletung.commonlib.utils.KeyboardUtil;
import com.kyletung.commonlib.utils.ToastUtil;
import com.kyletung.commonlib.view.android.BaseFrameLayout;
import com.kyletung.simplebookmovie.BuildConfig;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.search.SearchAdapter;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.client.request.MovieClient;
import com.kyletung.simplebookmovie.data.book.BookSubject;
import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.data.search.SearchBookData;
import com.kyletung.simplebookmovie.ui.book.BookDetailActivity;
import com.kyletung.simplebookmovie.ui.movie.MovieDetailActivity;
import com.kyletung.simplebookmovie.view.spinner.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/3/24<br>
 * <br>
 * 搜索页面
 */
public class SearchFragment extends BaseLoadFragment {

    @BindView(R.id.search_input)
    EditText mSearchInput;
    @BindView(R.id.search_type)
    NiceSpinner mSearchType;
    @BindView(R.id.search_icon)
    ImageView mSearchIcon;

//    @BindView(R.id.search_recent)
//    RecyclerView mSearchRecent;
//    @BindView(R.id.layout_recent)
//    FrameLayout mLayoutRecent;

    @BindView(R.id.swipe_target)
    RecyclerView mSearchResult;
    @BindView(R.id.layout_result)
    BaseFrameLayout mLayoutResult;

    private int mBookOrMovie = 0;    // 0 stands for book, 1 stands for movie

    private String mSearchContent;

    private SearchAdapter mSearchAdapter;

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
        // init swipe
        mLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.common_load_container);
        mLoadLayout.setEmptyView(R.string.load_empty_data, R.mipmap.default_data_empty);
        // init recycler
        mSearchResult.setItemAnimator(new DefaultItemAnimator());
        mSearchResult.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mSearchAdapter = new SearchAdapter(getActivity());
        mSearchResult.setAdapter(mSearchAdapter);
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
        mSearchAdapter.setOnBookClickListener(new SearchAdapter.OnBookClickListener() {
            @Override
            public void onClick(int position, BookSubject bookSubject) {
                BookDetailActivity.start(getActivity(), bookSubject, null);
            }
        });
        mSearchAdapter.setOnMovieClickListener(new SearchAdapter.OnMovieClickListener() {
            @Override
            public void onClick(int position, MovieSubject movieSubject) {
                MovieDetailActivity.start(getActivity(), movieSubject);
            }
        });
        // set swipe load
        setRefresh(false, false);
    }

    @OnClick({
            R.id.search_icon
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_icon:
                if (TextUtils.isEmpty(mSearchInput.getText())) {
                    ToastUtil.showToast(getActivity(), getString(R.string.search_input_empty));
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
        if (BuildConfig.DEBUG) ToastUtil.showToast(getActivity(), "Show Recent");
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
        if (BuildConfig.DEBUG) ToastUtil.showToast(getActivity(), "Hide Recent");
    }

    /**
     * 搜索内容
     *
     * @param content 内容
     */
    private void startSearch(String content) {
        mSearchContent = content;
        hideRecentSearch();
        mLayoutResult.startLoad();
        if (mBookOrMovie == 0) {
            mSearchAdapter.setModeBook();
            searchBook(content, 0);
        } else if (mBookOrMovie == 1) {
            mSearchAdapter.setModeMovie();
            searchMovie(content, 0);
        } else {
            mLayoutResult.stopLoad();
        }
    }

    /**
     * 搜索书籍内容
     *
     * @param content 搜索内容
     * @param start   插入位置
     */
    private void searchBook(String content, int start) {
        BookClient.getInstance().getBookSearch(content, start).subscribe(newSubscriber(new Action1<SearchBookData>() {
            @Override
            public void call(SearchBookData searchBookData) {
                loadComplete();
                mLayoutResult.stopLoad();
                putBookList(searchBookData, start);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mLayoutResult.stopLoad();
            }
        }));
    }

    /**
     * 处理书籍搜索结果
     *
     * @param data  搜索结果
     * @param start 插入位置
     */
    private void putBookList(SearchBookData data, int start) {
        if (start == 0) {
            if (data.getCount() != 0) {
                mLoadLayout.setShowEmptyView(false);
                mSearchAdapter.putBookList(data.getBooks());
            } else {
                mLoadLayout.setShowEmptyView(true);
            }
        } else {
            if (data.getCount() != 0) {
                mSearchAdapter.addBookList(data.getBooks());
            }
        }
        if (mSearchAdapter.getItemCount() < data.getTotal()) {
            setMoreEnable(true);
        } else {
            setMoreEnable(false);
        }
    }

    /**
     * 搜索影视内容
     *
     * @param content 内容
     * @param start   插入位置
     */
    private void searchMovie(String content, int start) {
        MovieClient.getInstance().getMovieSearch(content, start).subscribe(newSubscriber(new Action1<MovieTopData>() {
            @Override
            public void call(MovieTopData movieTopData) {
                loadComplete();
                mLayoutResult.stopLoad();
                putMovieList(movieTopData, start);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mLayoutResult.stopLoad();
            }
        }));
    }

    /**
     * 处理影视搜索结果
     *
     * @param data  搜索结果
     * @param start 插入位置
     */
    private void putMovieList(MovieTopData data, int start) {
        if (start == 0) {
            if (data.getCount() > 0) {
                mLoadLayout.setShowEmptyView(false);
                mSearchAdapter.putMovieList(data.getSubjects());
            } else {
                mLoadLayout.setShowEmptyView(true);
            }
        } else {
            if (data.getCount() > 0) {
                mSearchAdapter.addMovieList(data.getSubjects());
            }
        }
        if (mSearchAdapter.getItemCount() < data.getTotal()) {
            setMoreEnable(true);
        } else {
            setMoreEnable(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        clearSearchFocus();
    }

    @Override
    protected void onActionRefresh() {
        loadComplete();
    }

    @Override
    protected void onActionMore() {
        if (mBookOrMovie == 0) {
            searchBook(mSearchContent, mSearchAdapter.getItemCount());
        } else if (mBookOrMovie == 1) {
            searchMovie(mSearchContent, mSearchAdapter.getItemCount());
        } else {
            loadComplete();
        }
    }

}
