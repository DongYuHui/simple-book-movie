package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.client.api.MovieApi;
import com.kyletung.simplebookmovie.data.movie.MovieBoardData;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 影视相关的请求提
 */
public class MovieClient extends SimpleClient {

    private static final int REQUEST_COUNT = 20;

    private MovieApi mMovieApi;

    public MovieClient() {
        super();
        mMovieApi = getRetrofit().create(MovieApi.class);
    }

    public static MovieClient getInstance() {
        return new MovieClient();
    }

    /**
     * 获取影视详情
     *
     * @param movieId      影视 Id
     * @param responseImpl 返回接口实现
     */
    public void getMovieDetail(String movieId, IResponse<MovieDetailData> responseImpl) {
        mMovieApi.getMovieDetail(movieId).enqueue(newCallback(responseImpl));
    }

    /**
     * 获取影视列表
     *
     * @param start        开始点
     * @param responseImpl 返回接口实现
     */
    public void getMovieTop(int start, IResponse<MovieTopData> responseImpl) {
        mMovieApi.getMovieTop(start, REQUEST_COUNT).enqueue(newCallback(responseImpl));
    }

    /**
     * 获取影视榜单
     *
     * @param responseImpl 返回接口实现
     */
    public void getMovieBoard(IResponse<MovieBoardData> responseImpl) {
        mMovieApi.getMovieBoard().enqueue(newCallback(responseImpl));
    }

    /**
     * 获取影视搜索数据
     *
     * @param content      搜索内容
     * @param start        开始点
     * @param responseImpl 返回接口实现
     */
    public void getMovieSearch(String content, int start, IResponse<MovieTopData> responseImpl) {
        mMovieApi.getMovieSearch(content, start, REQUEST_COUNT).enqueue(newCallback(responseImpl));
    }

}
