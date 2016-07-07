package com.kyletung.simplebookmovie.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kyletung.simplebookmovie.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by DongYuhui on 2016/2/24.
 * Volley Util
 */
public class VolleyUtil {

    private volatile RequestQueue mRequestQueue;

    private static VolleyUtil mVolley;

    private VolleyUtil() {
        mRequestQueue = Volley.newRequestQueue(BaseApplication.getInstance());
        mRequestQueue.start();
    }

    /**
     * Single Instance
     *
     * @return VolleyUtil instance
     */
    public static VolleyUtil getInstance() {
        if (mVolley == null) {
            mVolley = new VolleyUtil();
        }
        return mVolley;
    }

    /**
     * post 方式请求 string
     *
     * @param tag           请求的 tag，用于以后取消请求。
     * @param url           请求的 url
     * @param listener      请求成功的监听器
     * @param errorListener 请求失败的监听器
     * @param params        post body
     */
    public void postString(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, final Map<String, String> params) {
        StringRequest requestString = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestString.setTag(tag);
        mRequestQueue.add(requestString);
    }

    /**
     * get 方式请求 string
     *
     * @param tag           请求 tag
     * @param url           请求 url
     * @param listener      请求成功的监听器
     * @param errorListener 请求失败的监听器
     */
    public void getString(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest requestString = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestString.setTag(tag);
        mRequestQueue.add(requestString);
    }

    /**
     * post 方式请求 json
     *
     * @param tag           请求的tag，用于以后取消请求。
     * @param url           请求 url
     * @param listener      请求成功监听器
     * @param errorListener 请求失败监听器
     * @param params        post body
     */
    public void postJson(Object tag, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map params) {
        JSONObject jsonObject = null;
        if (params != null) {
            try {
                jsonObject = new JSONObject(new Gson().toJson(params));
            } catch (JSONException e) {
                e.printStackTrace();
                errorListener.onErrorResponse(new VolleyError("Trans Post Body Error Exception"));
            }
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, listener, errorListener);
        jsonObjectRequest.setTag(tag);
        mRequestQueue.add(jsonObjectRequest);
    }

    /**
     * get 方式请求 json
     *
     * @param tag           请求的tag，用于以后取消请求。
     * @param url           请求 url
     * @param listener      请求成功监听器
     * @param errorListener 请求失败监听器
     * @param params        request body
     */
    public void getJson(Object tag, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map params) {
        JSONObject jsonObject = null;
        if (params != null) {
            try {
                jsonObject = new JSONObject(new Gson().toJson(params));
            } catch (JSONException e) {
                e.printStackTrace();
                errorListener.onErrorResponse(new VolleyError("Trans Post Body Error Exception"));
            }
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, listener, errorListener);
        jsonObjectRequest.setTag(tag);
        mRequestQueue.add(jsonObjectRequest);
    }

    /**
     * 取消请求
     *
     * @param tag 请求的 tag
     */
    public void removeAllRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

}
