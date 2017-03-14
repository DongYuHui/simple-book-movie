package com.kyletung.commonlib.view.android;

import com.airbnb.lottie.LottieAnimationView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/14 at 10:45<br>
 * FixMe
 */
public interface ILoadView {

    String DEFAULT_LOAD_FILE = "PinJump.json";

    /**
     * 对加载控件做初始化操作
     */
    void initLoad();

    /**
     * 设置加载用的动画文件
     *
     * @param file 文件
     */
    void setLoadFile(String file);

    /**
     * 当前是否在加载中
     *
     * @return 加载中返回 true，否则返回 false
     */
    boolean isLoading();

    /**
     * 开始加载
     */
    void startLoad();

    /**
     * 停止加载
     */
    void stopLoad();

    /**
     * 加载出错
     */
    void onLoadError(String error);

    /**
     * 销毁控件，回收
     */
    void destroyLoad();

    /**
     * 获取当前的 Lottie 加载控件
     *
     * @return 返回 Lottie 控件
     */
    LottieAnimationView getLoadView();

}
