package com.kyletung.commonlib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/06/27 at 15:27<br>
 * <br>
 * 封装 Gson 库，用于处理 Json 格式
 */
public class JsonUtil {

    private static JsonUtil mUtil;

    private Gson mJson;

    private JsonUtil() {
        //no instance
        mJson = new GsonBuilder().create();
    }

    public static JsonUtil getInstance() {
        if (mUtil == null) mUtil = new JsonUtil();
        return mUtil;
    }

    /**
     * 返回 Gson
     *
     * @return 返回当前对象
     */
    public Gson getGson() {
        return mJson;
    }

    /**
     * 将对象转化成 Json 字符串
     *
     * @param object 对象
     * @return 返回 Json 字符串
     */
    public String toJson(Object object) {
        return mJson.toJson(object);
    }

    /**
     * 将 Json 字符串转化成对象
     *
     * @param content 字符串
     * @param cls     类类型
     * @param <T>     类类型
     * @return 返回生成的对象
     */
    public <T> T fromJson(String content, Class<T> cls) {
        return mJson.fromJson(content, cls);
    }

    /**
     * 将 Json 字符串转化成 List
     *
     * @param content 字符串
     * @param <T>     类类型
     * @return 返回 List
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> toList(String content) {
        return mJson.fromJson(content, ArrayList.class);
    }

}
