package com.kyletung.commonlib.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/4/20<br>
 * <br>
 * 震动工具类
 * This class requires the caller to hold the permission
 * {@link android.Manifest.permission#VIBRATE}.
 */
public class VibrateUtil {

    /**
     * 震动
     *
     * @param context Context
     * @param time    震动时长
     * @return 方便取消
     */
    public static Vibrator vibrate(Context context, long time) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
        return vibrator;
    }

    /**
     * 震动一次时间序列，不重复
     *
     * @param context Context
     * @param times   震动序列
     * @return 方便取消
     */
    public static Vibrator vibrate(Context context, long... times) {
        return vibrate(context, -1, times);
    }

    /**
     * 震动一次时间序列，有调用者决定重复次数
     *
     * @param context Context
     * @param repeat  重复次数
     * @param times   震动序列
     * @return 方便取消
     */
    public static Vibrator vibrate(Context context, int repeat, long... times) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(times, repeat);
        return vibrator;
    }

}
