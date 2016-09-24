package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.data.user.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    /*
    https://www.douban.com/service/auth2/auth
    ?client_id=0208376c6d519a130618a64547f4ce39
    &redirect_uri=http://www.kyletung.com
    &response_type=code
    &scope=book_basic_r,book_basic_w,douban_basic_common,movie_basic_r
     */

    /*
    confirm:%E6%8E%88%E6%9D%83
    response_type:code
    redirect_url:http%3A%2F%2Fwww.kyletung.com
    user_passwd:foreachHer*100
    client_id:0208376c6d519a130618a64547f4ce39
    user_alias:dyh920827%40gmail.com
     */

    @GET("user/{userId}")
    Call<UserData> getUserData(@Path("userId") String userId);

//    @POST("https://www.douban.com/service/auth2/token?client_id={app_key}&client_secret={app_secret}&redirect_uri=http://www.kyletung.com&grant_type=authorization_code&code={code}")
//    Call<LoginData> getToken(@Path("app_key") String appKey, @Path("app_secret") String appSecret, @Path("code") String code);

    @POST("https://www.douban.com/service/auth2/token")
    Call<LoginData> getToken(@Query("client_id") String clientId, @Query("client_secret") String clientSecret, @Query("redirect_uri") String redirectUri, @Query("grant_type") String grantType, @Query("code") String code);

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

}
