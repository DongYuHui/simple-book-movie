package com.kyletung.simplebookmovie.util;

import com.kyletung.simplebookmovie.config.Constants;

import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 15:53<br>
 * <br>
 * 用于拼接 Url 的工具类
 */
public class UrlUtil {

    private static UrlUtil mUtil;

    private UrlUtil() {
        //no instance
    }

    public static UrlUtil getInstance() {
        if (mUtil == null) mUtil = new UrlUtil();
        return mUtil;
    }

    public String bindUrl(String url) {
        return String.format(Constants.BASE_URL, url);
    }

    public String buildUrl(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        stringBuilder.substring(0, stringBuilder.length() - 1);
        return String.format(Constants.BASE_URL, stringBuilder);
    }

}
