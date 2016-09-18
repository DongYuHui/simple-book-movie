package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.book.BookData;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.data.search.SearchBookData;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;

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
        mBookApi = getRetrofit().create(BookApi.class);
    }

    public static BookClient getInstance() {
        return new BookClient();
    }

    /**
     * 搜索书籍
     *
     * @param content      搜索内容
     * @param start        开始点
     * @param responseImpl 返回接口实现
     */
    public void getBookSearch(String content, int start, IResponse<SearchBookData> responseImpl) {
        mBookApi.getBookSearch(content, start, REQUEST_COUNT).enqueue(newCallback(responseImpl));
    }

    /**
     * 获取书籍列表
     *
     * @param userId       用户 Id
     * @param status       状态
     * @param start        开始点
     * @param responseImpl 返回接口实现
     */
    public void getBookData(String userId, String status, int start, IResponse<BookData> responseImpl) {
        mBookApi.getBookData(userId, status, start, REQUEST_COUNT).enqueue(newCallback(responseImpl));
    }

    /**
     * 获取书籍详情
     *
     * @param bookId       书籍 Id
     * @param responseImpl 返回接口实现
     */
    public void getBookDetail(String bookId, IResponse<BookDetailData> responseImpl) {
        mBookApi.getBookDetail(bookId).enqueue(newCallback(responseImpl));
    }

    /**
     * 获取书籍详情
     *
     * @param bookId       书籍 Id
     * @param userId       用户 Id
     * @param responseImpl 返回接口实现
     */
    public void getBookDetail(String bookId, String userId, IResponse<BookCollectionData> responseImpl) {
        mBookApi.getBookDetail(bookId, userId).enqueue(newCallback(responseImpl));
    }

}
