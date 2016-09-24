package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.data.user.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 账户相关请求
 */
public interface AccountApi {

    @GET("user/{userId}")
    Call<UserData> getUserData(@Path("userId") String userId);

//    @POST("https://www.douban.com/service/auth2/token?client_id={app_key}&client_secret={app_secret}&redirect_uri=http://www.kyletung.com&grant_type=authorization_code&code={code}")
//    Call<LoginData> getToken(@Path("app_key") String appKey, @Path("app_secret") String appSecret, @Path("code") String code);

    @POST("https://www.douban.com/service/auth2/token")
    Call<LoginData> getToken(@Query("client_id") String clientId, @Query("client_secret") String clientSecret, @Query("redirect_uri") String redirectUri, @Query("grant_type") String grantType, @Query("code") String code);

}
