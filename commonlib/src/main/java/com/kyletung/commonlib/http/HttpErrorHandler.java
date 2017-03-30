package com.kyletung.commonlib.http;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.kyletung.commonlib.BuildConfig;
import com.kyletung.commonlib.utils.ToastUtil;

import java.io.EOFException;
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

    private static final String ERROR_CONNECTION = "网络连接错误，请稍后重试";      // ConnectException，链接错误，无网络等
    private static final String ERROR_TIMEOUT = "网络连接超时，请稍后重试";         // SocketTimeoutException，链接超时错误
    private static final String ERROR_HTTP = "网络状态错误，请稍后重试";            // HttpException，401，404 等状态错误
    private static final String ERROR_EOF = "网络数据错误，请退出重试";             // EOFException，往往发生在测试阶段打印请求的过程中
    private static final String ERROR_JSON = "数据解析错误，请稍后再试";            // Json 数据解析错误
    private static final String ERROR_OTHER = "网络遇到意外错误，请稍后重试";       // Other

    public static void handle(Context context, Throwable e) {
        if (BuildConfig.DEBUG) e.printStackTrace();
        if (e instanceof ConnectException) {
            ToastUtil.showToast(context, ERROR_CONNECTION);
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showToast(context, ERROR_TIMEOUT);
        } else if (e instanceof EOFException) {
            ToastUtil.showToast(context, ERROR_EOF);
        } else if (e instanceof JsonParseException) {
            ToastUtil.showToast(context, ERROR_JSON);
        } else if (e instanceof NumberFormatException) {
            ToastUtil.showToast(context, ERROR_JSON);
        } else if (e instanceof HttpException) {
            ToastUtil.showToast(context, ERROR_HTTP);
        } else {
            ToastUtil.showToast(context, ERROR_OTHER);
        }
    }

}
