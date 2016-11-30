package com.kyletung.simplebookmovie.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.commonlib.main.BaseFragment;
import com.kyletung.commonlib.utils.ToastUtil;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookAdapter;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.data.book.BookItem;
import com.kyletung.simplebookmovie.utils.UserInfoUtil;
import com.kyletung.simplebookmovie.view.LinearOnScrollListener;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 20:17<br>
 * <br>
 * 书籍列表页面
 */
public class BookListFragment extends BaseFragment {

    private boolean mHasMore = true;

    private String mUserId;
    private String mStatus;

    private BookAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearOnScrollListener mOnScrollListener;

    public static BookListFragment newInstance(String status) {
        Bundle args = new Bundle();
        args.putString("status", status);
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_refresh_recycler;
    }

    @Override
    protected void initView(View view) {
        // init data
        Bundle bundle = getArguments();
        mUserId = new UserInfoUtil(getActivity()).readUserId();
        mStatus = bundle.getString("status");
        // init recycler
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        // init recycler
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new BookAdapter(getActivity(), R.layout.recycler_book_item, this);
        recyclerView.setAdapter(mAdapter);
        mOnScrollListener = new LinearOnScrollListener(linearLayoutManager, mAdapter);
        recyclerView.addOnScrollListener(mOnScrollListener);
        // set listener
        setListener();
    }

    @Override
    protected void business(View view) {
    }

    private void setListener() {
        mAdapter.setOnItemClickListener((position, bookId) -> {
            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra("userId", mUserId);
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });
        mRefreshLayout.setOnRefreshListener(() -> {
            mHasMore = true;
            getData(0);
        });
        mOnScrollListener.setOnLoadMore(() -> getData(mAdapter.getItemCount()));
        mRefreshLayout.post(() -> {
            mRefreshLayout.setRefreshing(true);
            mHasMore = true;
            getData(0);
        });
    }

    public void getDataSuccess(ArrayList<BookItem> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    public void getDataError(String error) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showToast(getActivity(), error);
    }

    public void getMoreSuccess(ArrayList<BookItem> list) {
        mOnScrollListener.loadComplete();
        mAdapter.addList(list);
        if (list.size() == 0) mHasMore = false;
    }

    public void getMoreError(String error) {
        mOnScrollListener.loadComplete();
        ToastUtil.showToast(getActivity(), error);
    }

    /**
     * 获取书籍列表数据
     *
     * @param start 开始点
     */
    private void getData(final int start) {

        if (mUserId == null || mStatus == null) return;

        if (!mHasMore) {
            if (start == 0) {
                mRefreshLayout.setRefreshing(false);
            } else {
                mOnScrollListener.loadComplete();
            }
            return;
        }

        BookClient.getInstance().getBookData(mUserId, mStatus, start).subscribe(newSubscriber(bookData -> {
            if (start == 0) {
                getDataSuccess(bookData.getCollections());
            } else {
                getMoreSuccess(bookData.getCollections());
            }
        }, throwable -> {
            if (start == 0) {
                getDataError(throwable.getMessage());
            } else {
                getMoreError(throwable.getMessage());
            }
        }));

    }

}
