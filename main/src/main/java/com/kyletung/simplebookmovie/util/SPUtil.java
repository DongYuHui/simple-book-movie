package com.kyletung.simplebookmovie.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 17:10<br>
 * <br>
 * SharedPreferences 工具类
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

    /**
     * 保存数据
     *
     * @param context 上下文
     * @param name    SP 名字
     * @param key     保存键
     * @param value   保存值
     */
    public void save(Context context, String name, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 保存数据
     *
     * @param context 上下文
     * @param name    SP 名字
     * @param key     保存键
     * @param value   保存值
     */
    public void save(Context context, String name, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 保存数据
     *
     * @param context 上下文
     * @param name    SP 名字
     * @param key     保存键
     * @param value   保存值
     */
    public void save(Context context, String name, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 保存数据
     *
     * @param context 上下文
     * @param name    SP 名字
     * @param map     包含键值对的 Map
     */
    public void save(Context context, String name, Map<String, String> map) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.apply();
    }

    /**
     * 读取数据
     *
     * @param context      上下文
     * @param name         SP 名字
     * @param key          保存键
     * @param defaultValue 默认值
     * @return 返回读取到的值或默认值
     */
    public String read(Context context, String name, String key, @Nullable String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 读取 int 值
     *
     * @param context      上下文
     * @param name         SP 名字
     * @param key          保存键
     * @param defaultValue 默认值
     * @return 返回读取到的值或默认值
     */
    public int read(Context context, String name, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 读取 long 值
     *
     * @param context      上下文
     * @param name         SP 名字
     * @param key          保存键
     * @param defaultValue 默认值
     * @return 返回读取到的值或默认值
     */
    public long read(Context context, String name, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

}
