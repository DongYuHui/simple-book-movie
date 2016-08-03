package com.kyletung.simplebookmovie.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/16 at 18:20<br>
 * <br>
 * FixMe
 */
public class VersionUtil {

    private static VersionUtil mUtil;

    private VersionUtil() {
        //no instance
    }

    public static VersionUtil getInstance() {
        if (mUtil == null) mUtil = new VersionUtil();
        return mUtil;
    }

    /**
     * 获取应用的版本名称
     *
     * @return 返回 版本名称
     * @throws PackageManager.NameNotFoundException
     */
    public String getVersion(Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
        return info.versionName;
    }

}
