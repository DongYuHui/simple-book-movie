package com.kyletung.simplebookmovie.client;

import com.kyletung.library.BaseClient;
import com.kyletung.library.BaseHost;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 项目所需请求封装类
 */
public abstract class SimpleClient extends BaseClient {

    public SimpleClient() {
        super(getSimpleHost());
    }

    private static BaseHost getSimpleHost() {
        return new BaseHost() {
            @Override
            public String getHost() {
                return "https://api.douban.com/v2/";
            }
        };
    }

}
