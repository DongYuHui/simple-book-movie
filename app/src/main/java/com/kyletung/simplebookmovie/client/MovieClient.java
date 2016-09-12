package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void getMovieDetail(String movieId) {
        mMovieApi.getMovieDetail(movieId).enqueue(new Callback<MovieDetailData>() {

            @Override
            public void onResponse(Call<MovieDetailData> call, Response<MovieDetailData> response) {
                System.out.println("test success");
            }

            @Override
            public void onFailure(Call<MovieDetailData> call, Throwable t) {
                System.out.println("test error");
            }

        });
    }

}
