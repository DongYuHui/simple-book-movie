package com.kyletung.simplebookmovie.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 17:10<br>
 * <br>
 * FixMe
 */
public class SPUtil {

    private static SPUtil mUtil;

    private SPUtil() {
        //no instance
    }

    public static SPUtil getInstance() {
        if (mUtil == null) mUtil = new SPUtil();
        return mUtil;
    }

    public void save(Context context, String name, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void save(Context context, String name, Map<String, String> map) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.apply();
    }

}
