package com.kyletung.simplebookmovie.client;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 书籍相关的请求类
 */
public class BookClient extends SimpleClient {

    private BookApi mBookApi;

    private BookClient() {
        super();
        mBookApi = getRetrofit().create(BookApi.class);
    }

    public static BookClient getInstance() {
        return new BookClient();
    }

}
