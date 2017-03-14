package com.kyletung.commonlib.http;

import android.content.Context;

import com.kyletung.commonlib.utils.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
        builder.addConverterFactory(GsonConverterFactory.create(JsonUtil.getInstance().getGson()));
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }

    private OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.cache(getCache(context));
        // don't follow redirects
        builder.followSslRedirects(false);
        builder.followRedirects(false);
        // set custom interceptor
        builder.addNetworkInterceptor(this::getInterceptor);
        // initView http log
        return builder.build();
    }

    /**
     * 获取缓存
     *
     * @param context context
     * @return 返回缓存
     */
    private Cache getCache(Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        return new Cache(file, 100 * 1024 * 1024);
    }

    /**
     * 由子类实现自定义网络拦截器
     *
     * @param chain Chain
     * @return 返回
     */
    protected abstract Response getInterceptor(Interceptor.Chain chain) throws IOException;

}
