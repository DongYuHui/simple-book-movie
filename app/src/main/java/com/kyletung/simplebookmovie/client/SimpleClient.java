package com.kyletung.simplebookmovie.client;

import com.kyletung.library.BaseClient;
import com.kyletung.library.BaseHost;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/12.
 * 项目所需请求封装类
 */
public abstract class SimpleClient extends BaseClient {

    public SimpleClient() {
        super(getSimpleHost());
    }

    /**
     * 获取基础域名
     *
     * @return 返回实现接口
     */
    private static BaseHost getSimpleHost() {
        return new BaseHost() {
            @Override
            public String getHost() {
                return "https://api.douban.com/v2/";
            }
        };
    }

    /**
     * 获取 Retrofit 的 Callback 接口
     *
     * @param responseImpl 用户实现的接口
     * @param <T>          成功得到的类
     * @return 返回 Retrofit Callback 接口实现
     */
    protected <T> Callback<T> newCallback(final IResponse<T> responseImpl) {
        return new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    // 接口返回成功
                    responseImpl.onResponse(response.body());
                } else {
                    // 返回出现错误
                    try {
                        responseImpl.onError(response.code(), response.errorBody().string());
                    } catch (IOException e) {
                        responseImpl.onError(-1, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                responseImpl.onError(-1, t.getMessage());
            }

        };
    }

}
