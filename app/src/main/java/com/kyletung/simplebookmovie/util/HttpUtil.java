package com.kyletung.simplebookmovie.util;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kyletung.simplebookmovie.BaseApplication;

import java.util.Map;

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

    private volatile RequestQueue mRequestQueue;

    private HttpUtil() {
        //no instance
        mRequestQueue = Volley.newRequestQueue(BaseApplication.getInstance());
        mRequestQueue.start();
    }

    public static HttpUtil getInstance() {
        if (mUtil == null) mUtil = new HttpUtil();
        return mUtil;
    }

    /**
     * Get 请求
     *
     * @param tag              Tag，便于取消请求
     * @param url              请求地址
     * @param onResultListener 请求回调
     */
    public void get(Object tag, String url, final OnResultListener onResultListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onResultListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResultListener.onError(error.getMessage());
            }
        });
        stringRequest.setTag(tag);
        mRequestQueue.add(stringRequest);
    }

    /**
     * Post 请求
     *
     * @param tag              Tag，便于取消请求
     * @param url              请求地址
     * @param onResultListener 请求回调
     * @param body             请求体，可以为空
     */
    public void post(Object tag, String url, final OnResultListener onResultListener, @Nullable final Map<String, String> body) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onResultListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResultListener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (body != null) {
                    return body;
                }
                return super.getParams();
            }
        };
        stringRequest.setTag(tag);
        mRequestQueue.add(stringRequest);
    }

    /**
     * 取消请求
     *
     * @param tag Tag
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    /**
     * 定义的接口，用于请求回调
     */
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
