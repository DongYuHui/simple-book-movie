package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.user.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 账户相关请求
 */
public interface AccountApi {

    @GET("user/{userId}")
    Call<UserData> getUserData(@Path("userId") String userId);

}
