package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.search.SearchBookData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 书籍相关的接口
 */
public interface BookApi {

    @GET("book/search")
    Call<SearchBookData> getBookSerach(@Query("q") String content, @Query("start") int start, @Query("count") int count);

}
