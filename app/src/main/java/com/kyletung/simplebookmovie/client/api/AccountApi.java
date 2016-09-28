package com.kyletung.simplebookmovie.client.api;

import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.data.user.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 账户相关请求
 */
public interface AccountApi {

    /*
    https://www.douban.com/service/auth2/token
    ?client_id=%s
    &client_secret=%s
    &redirect_uri=http://www.douban.com
    &grant_type=refresh_token
    &refresh_token=%s
     */

    @GET("user/{userId}")
    Call<UserData> getUserData(@Path("userId") String userId);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("https://www.douban.com/service/auth2/token")
    Call<LoginData> getToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("redirect_uri") String redirectUri,
            @Query("grant_type") String grantType,
            @Query("code") String code
    );

    @FormUrlEncoded
    @POST("https://www.douban.com/service/auth2/auth")
    Call<String> getCode(
            @Query("client_id") String clientId,
            @Query("redirect_uri") String redirectUri,
            @Query("response_type") String responseTypeFirst,
            @Query("scope") String scope,
            @Field("confirm") String confirm,
            @Field("response_type") String responseTypeSecond,
            @Field("redirect_url") String redirectUrl,
            @Field("client_id") String clientIdSecond,
            @Field("user_alias") String account,
            @Field("user_passwd") String password
    );

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("https://www.douban.com/service/auth2/token")
    Call<LoginData> refreshToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("redirect_uri") String redirectUri,
            @Query("grant_type") String grantType,
            @Query("refresh_token") String refreshToken
    );

}
