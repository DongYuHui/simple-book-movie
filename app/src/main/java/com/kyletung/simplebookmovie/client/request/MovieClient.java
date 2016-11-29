package com.kyletung.simplebookmovie.client.request;

import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.client.SimpleClient;
import com.kyletung.simplebookmovie.client.api.MovieApi;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.movie.MovieBoardData;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

import rx.Observable;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 影视相关的请求提
 */
public class MovieClient extends SimpleClient {

    private static final int REQUEST_COUNT = 20;

    private MovieApi mMovieApi;

    private MovieClient() {
        super();
        mMovieApi = getRetrofit(BaseApplication.getInstance()).create(MovieApi.class);
    }

    public static MovieClient getInstance() {
        return new MovieClient();
    }

    /**
     * 获取影视详情
     *
     * @param movieId      影视 Id
     */
    public Observable<MovieDetailData> getMovieDetail(String movieId) {
        return mMovieApi.getMovieDetail(movieId, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取影视列表
     *
     * @param start        开始点
     */
    public Observable<MovieTopData> getMovieTop(int start) {
        return mMovieApi.getMovieTop(start, REQUEST_COUNT, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取影视榜单
     */
    public Observable<MovieBoardData> getMovieBoard() {
        return mMovieApi.getMovieBoard(Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取影视搜索数据
     *
     * @param content      搜索内容
     * @param start        开始点
     */
    public Observable<MovieTopData> getMovieSearch(String content, int start) {
        return mMovieApi.getMovieSearch(content, start, REQUEST_COUNT, Constants.APP_KEY).compose(flatResult());
    }

}
