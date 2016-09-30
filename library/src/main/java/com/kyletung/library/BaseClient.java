package com.kyletung.library;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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

    protected Retrofit getRetrofit(Context context) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getClient(context));
        builder.baseUrl(mBaseHost.getHost());
        Gson gson = new GsonBuilder().setLenient().create();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    private OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.cache(getCache(context));
        builder.followRedirects(false);
        builder.followSslRedirects(false);
        // init http log
        return builder.build();
    }

    private Cache getCache(Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        return new Cache(file, 10 * 1024 * 1024);
    }

}
