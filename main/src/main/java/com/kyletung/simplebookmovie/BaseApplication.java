package com.kyletung.simplebookmovie;

import android.app.Application;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/28 at 14:32<br>
 * <br>
 * Application
 */
public class BaseApplication extends Application {

    private static BaseApplication mApp;

    public static BaseApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

}
