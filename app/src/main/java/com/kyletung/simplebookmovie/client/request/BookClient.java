package com.kyletung.simplebookmovie.client.request;

import android.support.annotation.Nullable;

import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.client.SimpleClient;
import com.kyletung.simplebookmovie.client.api.BookApi;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.book.BookData;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.data.search.SearchBookData;

import rx.Observable;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 书籍相关的请求类
 */
public class BookClient extends SimpleClient {

    private static final int REQUEST_COUNT = 20;

    private BookApi mBookApi;

    private BookClient() {
        super();
        mBookApi = getRetrofit(BaseApplication.getInstance()).create(BookApi.class);
    }

    public static BookClient getInstance() {
        return new BookClient();
    }

    /**
     * 搜索书籍
     *
     * @param content      搜索内容
     * @param start        开始点
     */
    public Observable<SearchBookData> getBookSearch(String content, int start) {
        return mBookApi.getBookSearch(content, start, REQUEST_COUNT, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取书籍列表
     *
     * @param userId       用户 Id
     * @param status       状态
     * @param start        开始点
     */
    public Observable<BookData> getBookData(String userId, String status, int start) {
        return mBookApi.getBookData(userId, status, start, REQUEST_COUNT, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取书籍详情
     *
     * @param bookId       书籍 Id
     */
    public Observable<BookDetailData> getBookDetail(String bookId) {
        return mBookApi.getBookDetail(bookId, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取书籍详情
     *
     * @param bookId       书籍 Id
     * @param userId       用户 Id
     */
    public Observable<BookCollectionData> getBookDetail(String bookId, @Nullable String userId) {
        return mBookApi.getBookDetail(bookId, userId, Constants.APP_KEY).compose(flatResult());
    }

}
