package com.kyletung.simplebookmovie.model;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

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
    private Activity mActivity;
    private Fragment mFragment;

    /**
     * 构造方法
     *
     * @param activity Activity
     */
    public BaseModel(Activity activity) {
        init();
        mActivity = activity;
        mContext = activity;
    }

    /**
     * 构造方法
     *
     * @param fragment Fragment
     */
    public BaseModel(Fragment fragment) {
        init();
        mFragment = fragment;
        mContext = fragment.getActivity();
    }

    /**
     * 初始化
     */
    private void init() {
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
     * 获取 Activity
     *
     * @return Activity
     */
    protected Activity getActivity() {
        return mActivity;
    }

    /**
     * 获取 Fragment
     *
     * @return Fragment
     */
    protected Fragment getFragment() {
        return mFragment;
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
