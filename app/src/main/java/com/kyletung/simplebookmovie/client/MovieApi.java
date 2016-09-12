package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 影视相关的接口
 */
public interface MovieApi {

    @GET("/movie/subject/{movieId}")
    Call<MovieDetailData> getMovieDetail(@Path("movieId") String movieId);

}
