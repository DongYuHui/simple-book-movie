package com.kyletung.simplebookmovie.model;

import android.content.Context;

import com.kyletung.simplebookmovie.util.HttpUtil;
import com.kyletung.simplebookmovie.util.JsonUtil;
import com.kyletung.simplebookmovie.util.UrlUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/29 at 14:25<br>
 * <br>
 * BaseModel
 */
public abstract class BaseModel {

    // 工具类
    private UrlUtil mUrlUtil;
    private JsonUtil mJsonUtil;
    private HttpUtil mHttpUtil;

    // 上下文环境
    private Context mContext;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseModel(Context context) {
        mContext = context;
        mUrlUtil = UrlUtil.getInstance();
        mJsonUtil = JsonUtil.getInstance();
        mHttpUtil = HttpUtil.getInstance();
    }

    /**
     * 获取 Context
     *
     * @return Context
     */
    protected Context getContext() {
        return mContext;
    }

    /**
     * 获取 UrlUtil
     *
     * @return UrlUtil
     */
    protected UrlUtil getUrlUtil() {
        return mUrlUtil;
    }

    /**
     * 获取 HttpUtil
     *
     * @return HttpUtil
     */
    protected HttpUtil getHttpUtil() {
        return mHttpUtil;
    }

    /**
     * 获取 JsonUtil
     *
     * @return JsonUtil
     */
    protected JsonUtil getJsonUtil() {
        return mJsonUtil;
    }

}
