package com.kyletung.commonlib.utils;

import android.util.Base64;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/17 at 18:51<br>
 * Base64 加密工具类
 */
public class Base64Util {

    /**
     * 加密
     *
     * @param content 内容
     * @return 返回加密内容
     */
    public static String encode(String content) {
        return Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
    }

    /**
     * 解密
     *
     * @param content 内容
     * @return 返回解密内容
     */
    public static String decode(String content) {
        return new String(Base64.decode(content.getBytes(), Base64.DEFAULT));
    }

}
