package com.kyletung.simplebookmovie.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MovieBoardAdapter;
import com.kyletung.simplebookmovie.data.movie.MovieItem;
import com.kyletung.simplebookmovie.model.movie.MovieBoardModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.moviedetail.MovieDetailActivity;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 17:04<br>
 * <br>
 * 北美票房榜 Fragment
 */
public class MovieBoardFragment extends BaseFragment implements IMovieBoardView {

    private MovieBoardAdapter mAdapter;
    private MovieBoardModel mModel;
    private SwipeRefreshLayout mRefreshLayout;

    public static MovieBoardFragment newInstance() {
        Bundle args = new Bundle();
        MovieBoardFragment fragment = new MovieBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_refresh_recycler;
    }

    @Override
    protected void init(View view) {
        // init views
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        // init recycler
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MovieBoardAdapter(getActivity(), R.layout.recycler_movie_item, this);
        recyclerView.setAdapter(mAdapter);
        // init model
        mModel = new MovieBoardModel(getActivity(), this);
        mModel.setDataInterface(this);
        // set listener
        setListener();
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(new MovieBoardAdapter.OnItemClickListener() {
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
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                mModel.getData();
            }
        });
    }

    @Override
    public void onDataSuccess(ArrayList<MovieItem> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.putList(list);
    }

    @Override
    public void onDataError(String error) {
        mRefreshLayout.setRefreshing(false);
    }

}
