package com.kyletung.simplebookmovie.util;

import android.os.Handler;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/27 at 16:49<br>
 * <br>
 * 网络请求工具类
 */
public class HttpUtil {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static HttpUtil mUtil;

    private OkHttpClient mHttpClient;

    private Handler mHandler;

    private HttpUtil() {
        //no instance
        mHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    public static HttpUtil getInstance() {
        if (mUtil == null) mUtil = new HttpUtil();
        return mUtil;
    }

    public void setClient(OkHttpClient httpClient) {
        mHttpClient = httpClient;
    }

    public String getSyn(Object tag, String url) {
        String result;
        Request request = new Request.Builder().url(url).tag(tag).build();
        try {
            Response response = mHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                result = null;
            }
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

    public String postSyn(Object tag, String url, String body) {
        String result;
        Request request = new Request.Builder().url(url).tag(tag).post(RequestBody.create(MEDIA_TYPE_JSON, body)).build();
        try {
            Response response = mHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                result = null;
            }
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

    public void getAsyn(Object tag, String url, final OnResultListener onResultListener) {
        Request request = new Request.Builder().url(url).tag(tag).build();
        mHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                final String error = e.getMessage();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onResultListener.onError(error);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onResultListener.onSuccess(result);
                        }
                    });
                } else {
                    final String error = response.toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onResultListener.onError(error);
                        }
                    });
                }
            }

        });
    }

    public void postAsyn(Object tag, String url, @Nullable String body, final OnResultListener onResultListener) {
        RequestBody postbody = RequestBody.create(null, new byte[0]);
        Request.Builder builder = new Request.Builder().url(url).method("POST",postbody).header("Content-Length", "0").tag(tag);
        Request request = builder.build();
        mHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                final String error = e.getMessage();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onResultListener.onError(error);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onResultListener.onSuccess(result);
                        }
                    });
                } else {
                    final String error = response.toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onResultListener.onError(error);
                        }
                    });
                }
            }

        });
    }

    public synchronized void cancelRequest(Object tag) {
        for (Call call : mHttpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }

    public interface OnResultListener {

        /**
         * http request success
         *
         * @param result success result
         */
        void onSuccess(String result);

        /**
         * http request error
         *
         * @param error error result
         */
        void onError(String error);

    }

}
