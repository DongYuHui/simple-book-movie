package com.kyletung.simplebookmovie.client;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/13.
 * Retrofit 反馈
 */
public interface IResponse<T> {

    /**
     * Retrofit 成功的回调
     *
     * @param result 结果
     */
    void onResponse(T result);

    /**
     * Retrofit 失败的回调
     *
     * @param code 错误代码
     * @param reason 错误原因
     */
    void onError(int code, String reason);

}
