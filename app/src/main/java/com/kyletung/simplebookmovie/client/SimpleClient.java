package com.kyletung.simplebookmovie.client;

import android.util.Log;

import com.kyletung.library.BaseClient;
import com.kyletung.library.BaseHost;
import com.kyletung.library.NetworkUtil;
import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 项目所需请求封装类
 */
public abstract class SimpleClient extends BaseClient {

    private static final String F_BREAK = " %n";
    private static final String F_URL = " %s";
    private static final String F_TIME = " in %.1fms";
    private static final String F_HEADERS = "%s";
    private static final String F_RESPONSE = F_BREAK + "Response: %d";
    private static final String F_BODY = "body: %s";

    private static final String F_BREAKER = F_BREAK + "-------------------------------------------" + F_BREAK;
    private static final String F_REQUEST_WITHOUT_BODY = F_URL + F_TIME + F_BREAK + F_HEADERS;
    private static final String F_RESPONSE_WITHOUT_BODY = F_RESPONSE + F_BREAK + F_HEADERS + F_BREAKER;
    private static final String F_REQUEST_WITH_BODY = F_URL + F_TIME + F_BREAK + F_HEADERS + F_BODY + F_BREAK;
    private static final String F_RESPONSE_WITH_BODY = F_RESPONSE + F_BREAK + F_HEADERS + F_BODY + F_BREAK + F_BREAKER;

    public SimpleClient() {
        super(getSimpleHost());
    }

    /**
     * 获取基础域名
     *
     * @return 返回实现接口
     */
    private static BaseHost getSimpleHost() {
        return () -> BuildConfig.BASE_URL;
    }

    @Override
    protected okhttp3.Response getInterceptor(Interceptor.Chain chain) throws IOException {
        // init network status
        boolean hasNetwork = NetworkUtil.hasNetwork(BaseApplication.getInstance());
        // init request
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        // common header
        builder.removeHeader("Pragma");
        builder.header("User-Agent", "NanxunWater");
        builder.header("Device", "Android");
        String cacheTime;
        if (hasNetwork) {
            cacheTime = "public, max-age=60";
        } else {
            cacheTime = "public, only-if-cached, max-stale=2419200, max-age=" + (7 * 24 * 60 * 60);
        }
        builder.header("Cache-Control", cacheTime);
        // debuggable log
        if (BuildConfig.DEBUG) {
            long timeStart = System.nanoTime();
            okhttp3.Response response = chain.proceed(builder.build());
            long timeEnd = System.nanoTime();
            MediaType contentType = null;
            String bodyString = "";
            if (response.body() != null) {
                contentType = response.body().contentType();
                bodyString = response.body().string();
            }
            // 请求响应时间
            double time = (timeEnd - timeStart) / 1e6d;
            if (request.method().equals("GET")) {
                Log.e("request-->", String.format("GET " + F_REQUEST_WITHOUT_BODY + F_RESPONSE_WITH_BODY, request.url(), time, request.headers(), response.code(), response.headers(), stringifyResponseBody(bodyString)));
            } else if (request.method().equals("POST")) {
                Log.e("request-->", String.format("POST " + F_REQUEST_WITH_BODY + F_RESPONSE_WITH_BODY, request.url(), time, request.headers(), stringifyRequestBody(request), response.code(), response.headers(), stringifyResponseBody(bodyString)));
            }
            if (response.body() != null) {
                ResponseBody body = ResponseBody.create(contentType, bodyString);
                return response.newBuilder().body(body).header("Cache-Control", cacheTime).removeHeader("Pragma").build();
            } else {
                return response;
            }
        } else {
            okhttp3.Response response = chain.proceed(builder.build());
            return response.newBuilder().removeHeader("Pragma").header("Cache-Control", cacheTime).build();
        }
    }

    /**
     * 过滤请求结果
     *
     * @param <T> 返回结果
     * @return 返回 Transformer
     */
    @SuppressWarnings("unchecked")
    protected <T> Observable.Transformer<T, T> flatResult() {
        return mTransformer;
    }

    /**
     * 具体的请求结果过滤过程
     */
    private final Observable.Transformer mTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable)o)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static String stringifyRequestBody(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private String stringifyResponseBody(String responseBody) {
        return responseBody;
    }

}
