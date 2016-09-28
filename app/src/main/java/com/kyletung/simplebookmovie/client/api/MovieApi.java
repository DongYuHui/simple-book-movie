package com.kyletung.simplebookmovie.client.api;

import com.kyletung.simplebookmovie.data.movie.MovieBoardData;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 影视相关的接口
 */
public interface MovieApi {

    @GET("movie/subject/{movieId}")
    Call<MovieDetailData> getMovieDetail(
            @Path("movieId") String movieId,
            @Query("apiKey") String appKey
    );

    @GET("movie/top250")
    Call<MovieTopData> getMovieTop(
            @Query("start") int start,
            @Query("count") int count,
            @Query("apiKey") String appKey
    );

    @GET("movie/us_box")
    Call<MovieBoardData> getMovieBoard(
            @Query("apiKey") String appKey
    );

    @GET("movie/search")
    Call<MovieTopData> getMovieSearch(
            @Query("q") String content,
            @Query("start") int start,
            @Query("count") int count,
            @Query("apiKey") String appKey
    );

}
