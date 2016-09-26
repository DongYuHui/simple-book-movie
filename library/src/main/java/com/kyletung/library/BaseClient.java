package com.kyletung.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
        builder.client(getClient());
        builder.baseUrl(mBaseHost.getHost());
        Gson gson = new GsonBuilder().setLenient().create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.followRedirects(false);
        builder.followSslRedirects(false);
        return builder.build();
    }

}
