package com.kyletung.simplebookmovie.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.search.SearchBookAdapter;
import com.kyletung.simplebookmovie.data.book.BookSubject;
import com.kyletung.simplebookmovie.event.BaseEvent;
import com.kyletung.simplebookmovie.event.EventCode;
import com.kyletung.simplebookmovie.model.search.SearchBookModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.bookdetail.BookDetailActivity;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.view.recycler.LinearOnScrollListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 20:52<br>
 * <br>
 * FixMe
 */
public class SearchBookFragment extends BaseFragment implements ISearchBookView {

    private String mContent;

    private SearchBookAdapter mAdapter;
    private SearchBookModel mModel;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearOnScrollListener mOnScrollListener;

    public static SearchBookFragment newInstance() {
        return new SearchBookFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_refresh_recycler;
    }

    @Override
    protected void init(View view) {
        // init views
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SearchBookAdapter(getActivity(), R.layout.recycler_book_item, this);
        recyclerView.setAdapter(mAdapter);
        mOnScrollListener = new LinearOnScrollListener(layoutManager, mAdapter);
        recyclerView.addOnScrollListener(mOnScrollListener);
        // init model
        mModel = new SearchBookModel(getActivity());
        mModel.setInterface(this);
        // set listener
        setListener();
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(new SearchBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String bookId) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("userId", "");
                intent.putExtra("bookId", bookId);
                startActivity(intent);
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mModel.search(mContent);
            }
        });
        mOnScrollListener.setOnLoadMore(new LinearOnScrollListener.OnLoadMore() {
            @Override
            public void onLoadMore() {
                mModel.getMore(mContent, mAdapter.getItemCount());
            }
        });
    }

    @Override
    public void onBookSuccess(ArrayList<BookSubject> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    @Override
    public void onBookError(String error) {
        mRefreshLayout.setRefreshing(false);
        BaseToast.toast(getActivity(), error);
    }

    @Override
    public void onMoreSuccess(ArrayList<BookSubject> list) {
        mOnScrollListener.loadComplete();
        mAdapter.addList(list);
    }

    @Override
    public void onMoreError(String error) {
        mOnScrollListener.loadComplete();
        BaseToast.toast(getActivity(), error);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {
        if (event.getWhat() == EventCode.WHAT_SEARCH && event.getCode() == EventCode.CODE_SEARCH_ALL) {
            mContent = (String) event.getObject();
            mRefreshLayout.setRefreshing(true);
            mModel.search(mContent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
