package com.kyletung.simplebookmovie.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.commonlib.main.BaseLoadFragment;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.movie.MovieTopAdapter;
import com.kyletung.simplebookmovie.client.request.MovieClient;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 16:56<br>
 * <br>
 * 电影 Top 排行榜的 Fragment
 */
public class MovieTopFragment extends BaseLoadFragment {

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private MovieTopAdapter mAdapter;

    public static MovieTopFragment newInstance() {
        Bundle args = new Bundle();
        MovieTopFragment fragment = new MovieTopFragment();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MovieTopAdapter(getActivity(), R.layout.recycler_movie_item);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void business(View view) {
        mAdapter.setOnItemClickListener((position, movieSubject) -> {
//            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
//            intent.putExtra("movieId", movieId);
//            startActivity(intent);
            MovieDetailActivity.start(getActivity(), movieSubject);
        });
        setRefresh(true, true);
        autoRefresh();
    }

    @Override
    protected void onActionRefresh() {
        MovieClient.getInstance().getMovieTop(0).subscribe(newSubscriber(movieTopData -> {
            loadComplete();
            mAdapter.putList(movieTopData.getSubjects());
            mLoadLayout.setShowEmptyView(movieTopData.getTotal() == 0);
            judgeResult(movieTopData);
        }));
    }

    @Override
    protected void onActionMore() {
        MovieClient.getInstance().getMovieTop(mAdapter.getItemCount()).subscribe(newSubscriber(movieTopData -> {
            loadComplete();
            mAdapter.addList(movieTopData.getSubjects());
            judgeResult(movieTopData);
        }));
    }

    /**
     * 判断结果列表，查看是否还有更多
     */
    private void judgeResult(MovieTopData data) {
        if (mAdapter.getItemCount() < data.getTotal()) {
            setMoreEnable(true);
        } else {
            setMoreEnable(false);
        }
    }

}
