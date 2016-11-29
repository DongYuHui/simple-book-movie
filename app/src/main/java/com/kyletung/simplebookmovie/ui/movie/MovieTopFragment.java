package com.kyletung.simplebookmovie.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MovieTopAdapter;
import com.kyletung.simplebookmovie.client.request.MovieClient;
import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.utils.BaseToast;
import com.kyletung.simplebookmovie.view.recycler.LinearOnScrollListener;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 16:56<br>
 * <br>
 * 电影 Top 排行榜的 Fragment
 */
public class MovieTopFragment extends BaseFragment {

    private MovieTopAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearOnScrollListener mOnScrollListener;

    private boolean mHasMore = true;

    public static MovieTopFragment newInstance() {
        Bundle args = new Bundle();
        MovieTopFragment fragment = new MovieTopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_refresh_recycler;
    }

    @Override
    protected void init(View view) {
        // init refresh
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        // init recycler
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MovieTopAdapter(getActivity(), R.layout.recycler_movie_item, this);
        recyclerView.setAdapter(mAdapter);
        mOnScrollListener = new LinearOnScrollListener(layoutManager, mAdapter);
        recyclerView.addOnScrollListener(mOnScrollListener);
        // set listener
        setListener();
    }

    private void setListener() {
        mAdapter.setOnItemClickListener((position, movieId) -> {
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movieId", movieId);
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

    public void onDataSuccess(ArrayList<MovieSubject> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    public void onDataError(String error) {
        mRefreshLayout.setRefreshing(false);
        BaseToast.toast(getActivity(), error);
    }

    public void onMoreSuccess(ArrayList<MovieSubject> list) {
        mOnScrollListener.loadComplete();
        if (list.size() > 0) {
            mAdapter.addList(list);
        } else {
            mHasMore = false;
        }
    }

    public void onMoreError(String error) {
        mOnScrollListener.loadComplete();
        BaseToast.toast(getActivity(), error);
    }

    private void getData(final int start) {
        if (!mHasMore) {
            if (start == 0) {
                mRefreshLayout.setRefreshing(false);
            } else {
                mOnScrollListener.loadComplete();
            }
            return;
        }

        MovieClient.getInstance().getMovieTop(start).subscribe(newSubscriber(movieTopData -> {
            if (start == 0) {
                onDataSuccess(movieTopData.getSubjects()); // 刷新
            } else {
                onMoreSuccess(movieTopData.getSubjects()); // 更多
            }
        }, throwable -> {
            if (start == 0) {
                onDataError(throwable.getMessage());
            } else {
                onMoreError(throwable.getMessage());
            }
        }));

    }

}
