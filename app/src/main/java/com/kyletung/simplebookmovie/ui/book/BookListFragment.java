package com.kyletung.simplebookmovie.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookAdapter;
import com.kyletung.simplebookmovie.data.book.BookItem;
import com.kyletung.simplebookmovie.model.book.BookModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.bookdetail.BookDetailActivity;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.view.recycler.LinearOnScrollListener;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 20:17<br>
 * <br>
 * FixMe
 */
public class BookListFragment extends BaseFragment implements IBookView {

    private String mUserId;

    private BookModel mModel;
    private BookAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearOnScrollListener mOnScrollListener;

    public static BookListFragment newInstance(String userId, String status) {
        Bundle args = new Bundle();
        args.putString("userId", userId);
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
    protected void init(View view) {
        // init data
        Bundle bundle = getArguments();
        mUserId = bundle.getString("userId");
        String status = bundle.getString("status");
        // init recycler
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        // init model
        mModel = new BookModel(getActivity());
        mModel.setInterface(this, status, mUserId);
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

    private void setListener() {
        mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String bookId) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("userId", mUserId);
                intent.putExtra("bookId", bookId);
                startActivity(intent);
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mModel.getData();
            }
        });
        mOnScrollListener.setOnLoadMore(new LinearOnScrollListener.OnLoadMore() {
            @Override
            public void onLoadMore() {
                mModel.getMore(mAdapter.getItemCount());
            }
        });
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                mModel.getData();
            }
        });
    }

    @Override
    public void onDataSuccess(ArrayList<BookItem> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    @Override
    public void onDataError(String error) {
        mRefreshLayout.setRefreshing(false);
        BaseToast.toast(getActivity(), error);
    }

    @Override
    public void onMoreSuccess(ArrayList<BookItem> list) {
        mOnScrollListener.loadComplete();
        mAdapter.addList(list);
    }

    @Override
    public void onMoreError(String error) {
        mOnScrollListener.loadComplete();
        BaseToast.toast(getActivity(), error);
    }

}
