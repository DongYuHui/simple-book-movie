package com.kyletung.commonlib.main;

import com.kyletung.commonlib.BuildConfig;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/2 at 11:08<br>
 * 应用的一些相关配置信息
 */
public class AppConfig {

    private static final boolean mDebuggable = BuildConfig.DEBUG;

    /**
     * 是否可调试
     *
     * @return 是否可调试
     */
    public static boolean debuggable() {
        return mDebuggable;
    }

    //

}
