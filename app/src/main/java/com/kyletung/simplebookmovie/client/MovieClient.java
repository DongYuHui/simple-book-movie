package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 影视相关的请求提
 */
public class MovieClient extends SimpleClient {

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

}
