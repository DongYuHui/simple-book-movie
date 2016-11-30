package com.kyletung.commonlib.http;

import android.content.Context;

import com.kyletung.commonlib.main.AppConfig;
import com.kyletung.commonlib.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/9 at 15:18<br>
 * 统一处理网络请求错误
 */
public class HttpErrorHandler {

    private static final String ERROR_CONNECTION = "网络连接错误，请稍后重试";
    private static final String ERROR_TIMEOUT = "网络连接超时，请稍后重试";
    private static final String ERROR_HTTP = "网络状态错误，请稍后重试";

    public static void handle(Context context, Throwable e) {
        if (AppConfig.debuggable()) e.printStackTrace();
        if (e instanceof ConnectException) {
            ToastUtil.showToast(context, ERROR_CONNECTION);
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showToast(context, ERROR_TIMEOUT);
        } else if (e instanceof HttpException) {
            ToastUtil.showToast(context, ERROR_HTTP);
        } else {
            ToastUtil.showToast(context, e.getMessage());
        }
    }

}
