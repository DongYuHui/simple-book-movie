package com.kyletung.simplebookmovie.ui.book;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.commonlib.main.BaseLoadFragment;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookAdapter;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.data.book.BookData;
import com.kyletung.simplebookmovie.utils.UserInfoUtil;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 20:17<br>
 * <br>
 * 书籍列表页面
 */
public class BookListFragment extends BaseLoadFragment {

    private String mUserId;
    private String mStatus;

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private BookAdapter mAdapter;

    public static BookListFragment newInstance(String status) {
        Bundle args = new Bundle();
        args.putString("status", status);
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.common_load_layout;
    }

    @Override
    protected void initView(View view) {
        // init data
        Bundle bundle = getArguments();
        mUserId = new UserInfoUtil(getActivity()).readUserId();
        mStatus = bundle.getString("status");
        // init load layout
        initLoadLayout(view, R.id.common_load_container, R.string.load_empty_data, R.mipmap.default_data_empty);
        // init recycler
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new BookAdapter(getActivity(), R.layout.recycler_book_item);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void business(View view) {
        mAdapter.setOnItemClickListener((cover, position, bookSubject) -> {
            BookDetailActivity.start(getActivity(), bookSubject, mUserId);
        });
        setRefresh(true, true);
        autoRefresh();
    }

    @Override
    protected void onActionRefresh() {
        BookClient.getInstance().getBookData(mUserId, mStatus, 0).subscribe(newSubscriber(bookData -> {
            loadComplete();
            mAdapter.putList(bookData.getCollections());
            judgeResult(bookData);
            mLoadLayout.setShowEmptyView(bookData.getTotal() == 0);
        }));
    }

    @Override
    protected void onActionMore() {
        BookClient.getInstance().getBookData(mUserId, mStatus, mAdapter.getItemCount()).subscribe(newSubscriber(bookData -> {
            loadComplete();
            mAdapter.addList(bookData.getCollections());
            judgeResult(bookData);
        }));
    }

    /**
     * 判断结果列表，查看是否还有更多
     */
    private void judgeResult(BookData data) {
        if (mAdapter.getItemCount() < data.getTotal()) {
            setMoreEnable(true);
        } else {
            setMoreEnable(false);
        }
    }

}
