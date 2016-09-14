package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.search.SearchBookData;

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
        mBookApi.getBookSerach(content, start, REQUEST_COUNT).enqueue(newCallback(responseImpl));
    }

}
