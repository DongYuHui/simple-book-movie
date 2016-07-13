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
import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.model.movie.MovieTopModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.moviedetail.MovieDetailActivity;
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
public class MovieTopFragment extends BaseFragment implements IMovieTopView {

    private MovieTopModel mModel;
    private MovieTopAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearOnScrollListener mOnScrollListener;

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
        // init model
        mModel = new MovieTopModel(getActivity(), this);
        mModel.setDataInterface(this);
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
        mAdapter.setOnItemClickListener(new MovieTopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String movieId) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movieId", movieId);
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
    public void onDataSuccess(ArrayList<MovieSubject> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    @Override
    public void onDataError(String error) {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onMoreSuccess(ArrayList<MovieSubject> list) {
        mOnScrollListener.loadComplete();
        mAdapter.addList(list);
    }

    @Override
    public void onMoreError(String error) {
        mOnScrollListener.loadComplete();
    }

}
