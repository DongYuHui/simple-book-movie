package com.kyletung.commonlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/26 at 21:43<br>
 * <br>
 * 用于获取当前手机相关的信息的工具类
 */
public class AppInfoUtil {

    private AppInfoUtil() {
        //no instance
    }

    /**
     * 获取应用的版本名称
     *
     * @return 返回 版本名称
     * @throws PackageManager.NameNotFoundException
     */
    public static String getVersion(Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
        return info.versionName;
    }

    /**
     * 获取手机的品牌
     *
     * @return 返回手机品牌
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机的型号
     *
     * @return 返回手机型号
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取当前系统 Api Level
     *
     * @return 返回 Api Level 整型
     */
    public static int getApiVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取当前手机屏幕的宽度
     *
     * @param context 上下文
     * @return 返回手机屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取当前手机屏幕的高度
     *
     * @param context 上下文
     * @return 返回手机屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取当前手机的屏幕密度
     *
     * @param context 上下文
     * @return 返回手机屏幕密度
     */
    public static int getScreenDestiny(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

}
