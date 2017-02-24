package com.kyletung.simplebookmovie.client;

import android.util.Log;

import com.kyletung.commonlib.http.BaseClient;
import com.kyletung.commonlib.http.BaseHost;
import com.kyletung.commonlib.utils.NetworkUtil;
import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.BuildConfig;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
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

    private static final String TAG = "SimpleBookMovie";

    /**
     * 用于格式化输出网络请求
     */
    private static final String OUTPUT = "%1$s\n-\n%2$s\n-\n%3$s";

    /**
     * 用于转换 Request 的 body 字符串
     */
    private static final Buffer BUFFER = new Buffer();

    public SimpleClient() {
        super(getSimpleHost());
    }

    public SimpleClient(BaseHost host) {
        super(host);
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
        okhttp3.Request request = chain.request();
        okhttp3.Request.Builder requestBuilder = request.newBuilder();
        if (NetworkUtil.hasNetwork(BaseApplication.getInstance())) {
            requestBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control", "public, max-age=10");
        } else {
            requestBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control", "public, max-age=604800"); // 无网络时缓存一个星期
        }
        request = requestBuilder.build();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        okhttp3.Response.Builder responseBuilder = response.newBuilder();
        if (NetworkUtil.hasNetwork(BaseApplication.getInstance())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control", "public, max-age=10");
        } else {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .addHeader("Cache-Control", "public, max-age=604800"); // 无网络时缓存一个星期
        }
        response = responseBuilder.build();
        if (BuildConfig.DEBUG) {
            response = printRequestAndResponse(request, response, endTime - startTime);
        }
        return response;
    }

    /**
     * 将 Request 和 Response 的大致内容输出
     *
     * @param request  Request
     * @param response Response
     * @param time     请求时间
     * @return 返回 Response，因为 Response 的 body 只能读取一次，所以读取打印之后需要再塞回去生成新的 Response
     */
    private okhttp3.Response printRequestAndResponse(okhttp3.Request request, okhttp3.Response response, long time) {
        String requestBody = "";
        String responseBody = "";
        try {
            if (request.body() != null) {
                request.body().writeTo(BUFFER);
                requestBody = BUFFER.readUtf8();
            }
            ResponseBody body = response.body();
            if (body != null) {
                responseBody = unicodeToString(body.string());
                response = response.newBuilder().body(ResponseBody.create(body.contentType(), responseBody)).build();
            }
        } catch (IOException ignored) {
        }
        Log.e(TAG, ">>>>>>> Http Request Start >>>>>>>");
        Log.e(TAG, String.format(
                OUTPUT,
                String.valueOf(time) + "ms",
                request.url() + "\n" + request.headers().toString() + "\n" + requestBody,
                response.headers().toString() + "\n" + responseBody
        ));
        Log.e(TAG, "<<<<<<<  Http Request End  <<<<<<<");
        return response;
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
            return ((Observable) o)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * Unicode 转成字符串
     *
     * @param str 输入
     * @return 返回字符串
     */
    private static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

}
