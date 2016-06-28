package com.kyletung.simplebookmovie.util;

import java.util.Map;

import okhttp3.OkHttpClient;

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

    private static HttpUtil mUtil;

    private OkHttpClient mHttpClient;

    private HttpUtil() {
        //no instance
        mHttpClient = new OkHttpClient();
    }

    public static HttpUtil getInstance() {
        if (mUtil == null) mUtil = new HttpUtil();
        return mUtil;
    }

//    public String getSyn(String url, Map params) {
//
//    }
//
//    public String postSyn(String url, Map params, Map body) {
//
//    }

    public void getAsyn(String url, Map params, OnResultListener onResultListener) {

    }

    public void postAsyn(String url, Map params, Map body, OnResultListener onResultListener) {

    }

    public void cancelRequest(Object object) {

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
