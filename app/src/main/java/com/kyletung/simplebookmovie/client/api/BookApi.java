package com.kyletung.simplebookmovie.client.api;

import com.kyletung.simplebookmovie.data.book.BookData;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.data.search.SearchBookData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 书籍相关的接口
 */
public interface BookApi {

    @GET("book/search")
    Observable<SearchBookData> getBookSearch(
            @Query("q") String content,
            @Query("start") int start,
            @Query("count") int count,
            @Query("apiKey") String appKey
    );

    @GET("book/user/{userId}/collections")
    Observable<BookData> getBookData(
            @Path("userId") String userId,
            @Query("status") String status,
            @Query("start") int start,
            @Query("count") int count,
            @Query("apiKey") String appKey
    );

    @GET("book/{bookId}")
    Observable<BookDetailData> getBookDetail(
            @Path("bookId") String bookId,
            @Query("apiKey") String appKey
    );

    @GET("book/{bookId}/collection")
    Observable<BookCollectionData> getBookDetail(
            @Path("bookId") String bookId,
            @Query("user_id") String userId,
            @Query("apiKey") String appKey
    );

}
