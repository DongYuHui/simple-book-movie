package com.kyletung.commonlib.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/1 at 16:32<br>
 * 存储相关的工具类
 */
public class StorageUtil {

    /**
     * 判断 SD卡 是否存在
     *
     * @return 返回是否存在
     */
    public static boolean isCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取 SD卡 路径
     *
     * @return 返回路径，没有则为 null
     */
    public static String getStoragePath() {
        if (!isCardExist()) return null;
        File path = Environment.getExternalStorageDirectory();
        if (path != null && !TextUtils.isEmpty(path.getAbsolutePath())) {
            return path.getAbsolutePath();
        } else {
            return null;
        }
    }

    /**
     * 创建目录
     *
     * @param path 目录路径
     * @return 返回是否成功
     */
    public static boolean makeDirctory(String path) {
        return new File(path).mkdir();
    }

    /**
     * 获取 /data/data/<package name>/cache 目录
     *
     * @param context Context
     * @return 返回缓存目录
     */
    public static String getCacheDir(Context context) {
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * 获取 /data/data/<package name>/files 目录
     *
     * @param context Context
     * @return 返回 Files 目录
     */
    public static String getFilesDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

}
