package com.kyletung.simplebookmovie.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.commonlib.main.BaseLoadFragment;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MovieBoardAdapter;
import com.kyletung.simplebookmovie.client.request.MovieClient;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 17:04<br>
 * <br>
 * 北美票房榜 Fragment
 */
public class MovieBoardFragment extends BaseLoadFragment {

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private MovieBoardAdapter mAdapter;

    public static MovieBoardFragment newInstance() {
        Bundle args = new Bundle();
        MovieBoardFragment fragment = new MovieBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.common_load_layout;
    }

    @Override
    protected void initView(View view) {
        // init load layout
        initLoadLayout(view, R.id.common_load_container, R.string.load_empty_data, R.mipmap.default_data_empty);
        // init recycler
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MovieBoardAdapter(getActivity(), R.layout.recycler_movie_item);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void business(View view) {
        mAdapter.setOnItemClickListener((position, movieSubject) -> {
            MovieDetailActivity.start(getActivity(), movieSubject);
        });
        // set refresh
        setRefresh(true, false);
        autoRefresh();
    }

    @Override
    protected void onActionRefresh() {
        MovieClient.getInstance().getMovieBoard().subscribe(newSubscriber(movieBoardData -> {
            loadComplete();
            mAdapter.putList(movieBoardData.getSubjects());
        }));
    }

    @Override
    protected void onActionMore() {
    }

}
