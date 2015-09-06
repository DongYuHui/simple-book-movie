package com.kyletung.doubanbookmovie;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    private static Context context;

    private static RequestQueue requestQueue;

    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        requestQueue = Volley.newRequestQueue(context);
        sharedPreferences = getSharedPreferences("AccessToken", MODE_PRIVATE);
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    //以下是围绕 access token 和 user 信息的方法

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static String getUserId() {
        return sharedPreferences.getString("userId", "null");
    }

    public static String getAccessToken() {
        return sharedPreferences.getString("accessToken", "null");
    }

    public static String getRefreshToken() {
        return sharedPreferences.getString("refreshToken", "null");
    }
}
