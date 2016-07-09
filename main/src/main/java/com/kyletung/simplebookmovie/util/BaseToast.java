package com.kyletung.simplebookmovie.util;

import android.content.Context;
import android.widget.Toast;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 17:38<br>
 * <br>
 * 处理 Toast 提示的工具类
 */
public class BaseToast {

    /**
     * 弹出短时 Toast
     *
     * @param context 上下文
     * @param content 提示内容
     */
    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
