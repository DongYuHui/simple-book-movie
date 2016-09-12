package com.kyletung.library;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * All Rights Reserved by Author.
 * Created by DongYuHui on 2016/9/12.
 * 基础 Retrofit 网络封装类
 */
public abstract class BaseClient {

    private BaseHost mBaseHost;

    public BaseClient(BaseHost host) {
        this.mBaseHost = host;
    }

    public Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(mBaseHost.getHost());
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

}
