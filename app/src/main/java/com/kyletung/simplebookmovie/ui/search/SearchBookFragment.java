package com.kyletung.simplebookmovie.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.commonlib.main.BaseLoadFragment;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.search.SearchBookAdapter;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.data.search.SearchBookData;
import com.kyletung.simplebookmovie.event.BaseEvent;
import com.kyletung.simplebookmovie.event.EventCode;
import com.kyletung.simplebookmovie.ui.book.BookDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 20:52<br>
 * <br>
 * 搜索书籍页面
 */
public class SearchBookFragment extends BaseLoadFragment {

    private String mContent;

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private SearchBookAdapter mAdapter;

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
        return R.layout.common_load_layout;
    }

    @Override
    protected void initView(View view) {
        // init load layout
        initLoadLayout(view, R.id.common_load_container, R.string.load_empty_data, R.mipmap.default_data_empty);
        // init views
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SearchBookAdapter(getActivity(), R.layout.recycler_book_item);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void business(View view) {
        mAdapter.setOnItemClickListener((position, bookId) -> {
            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra("userId", "");
            intent.putExtra("bookId", bookId);
            startActivity(intent);
        });
        // set refresh
        setRefresh(true, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {
        if (event.getWhat() == EventCode.WHAT_SEARCH && event.getCode() == EventCode.CODE_SEARCH_ALL) {
            mContent = (String) event.getObject();
            autoRefresh();
        }
    }

    @Override
    protected void onActionRefresh() {
        BookClient.getInstance().getBookSearch(mContent, 0).subscribe(newSubscriber(searchBookData -> {
            loadComplete();
            mAdapter.putList(searchBookData.getBooks());
            mLoadLayout.setShowEmptyView(searchBookData.getTotal() == 0);
            judgeResult(searchBookData);
        }));
    }

    @Override
    protected void onActionMore() {
        BookClient.getInstance().getBookSearch(mContent, mAdapter.getItemCount()).subscribe(newSubscriber(searchBookData -> {
            loadComplete();
            mAdapter.addList(searchBookData.getBooks());
            judgeResult(searchBookData);
        }));
    }

    /**
     * 判断结果列表，查看是否还有更多
     */
    private void judgeResult(SearchBookData data) {
        if (mAdapter.getItemCount() < data.getTotal()) {
            setMoreEnable(true);
        } else {
            setMoreEnable(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
