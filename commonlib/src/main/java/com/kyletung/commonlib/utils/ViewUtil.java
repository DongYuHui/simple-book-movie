package com.kyletung.commonlib.utils;

import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/4/20<br>
 * <br>
 * 在自定义 View 或者对 View 做一些处理时能够用到的一些方法
 */
public class ViewUtil {

    /**
     * 判断触控事件是否在某 View 上
     * @param ev        触控事件
     * @param target    目标控件
     * @return 返回是否在 View 上
     */
    public static boolean isTouchInView(MotionEvent ev, View target) {
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        RectF rect = new RectF(location[0], location[1], location[0] + target.getMeasuredWidth(), location[1] + target.getMeasuredHeight());
        return rect.contains(ev.getRawX(), ev.getRawY());
    }

}
