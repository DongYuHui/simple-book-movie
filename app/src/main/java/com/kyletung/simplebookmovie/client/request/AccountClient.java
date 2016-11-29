package com.kyletung.simplebookmovie.client.request;

import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.client.SimpleClient;
import com.kyletung.simplebookmovie.client.api.AccountApi;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.data.user.UserData;

import rx.Observable;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 用户资料相关
 */
public class AccountClient extends SimpleClient {

    private static final String REQUEST_REDIRECT_URI_MINE = "http://www.kyletung.com";
    private static final String REQUEST_REDIRECT_URI_OFFICIAL = "http://www.douban.com";
    private static final String REQUEST_GRANT_TYPE_GET = "authorization_code";
    private static final String REQUEST_GRANT_TYPE_REFRESH = "refresh_token";
    private static final String REQUEST_RESPONSE_TYPE = "code";
    private static final String REQUEST_SCOPE = "book_basic_r,book_basic_w,douban_basic_common,movie_basic_r";
    private static final String REQUEST_CONFIRM = "授权";

    private AccountApi mAccountApi;

    private AccountClient() {
        super();
        mAccountApi = getRetrofit(BaseApplication.getInstance()).create(AccountApi.class);
    }

    public static AccountClient getInstance() {
        return new AccountClient();
    }

    /**
     * 获取用户资料
     *
     * @param userId       用户 Id
     */
    public Observable<UserData> getUserData(String userId) {
        return mAccountApi.getUserData(userId, Constants.APP_KEY).compose(flatResult());
    }

    /**
     * 获取 AuthorizationCode
     *
     * @param account      账号
     * @param password     密码
     */
    public Observable<String> getCode(String account, String password) {
        return mAccountApi.getCode(
                Constants.APP_KEY,
                REQUEST_REDIRECT_URI_MINE,
                REQUEST_RESPONSE_TYPE,
                REQUEST_SCOPE,
                REQUEST_CONFIRM,
                REQUEST_RESPONSE_TYPE,
                REQUEST_REDIRECT_URI_MINE,
                Constants.APP_KEY,
                account,
                password
        ).compose(flatResult());
    }

    /**
     * 根据 AuthorizationCode 获取 AccessToken
     *
     * @param code         AuthorizationCode
     */
    public Observable<LoginData> getToken(String code) {
        return mAccountApi.getToken(
                Constants.APP_KEY,
                Constants.APP_SECRET,
                REQUEST_REDIRECT_URI_MINE,
                REQUEST_GRANT_TYPE_GET,
                code
        ).compose(flatResult());
    }

    /**
     * 根据 RefreshToken 获取
     *
     * @param refreshCode  RefreshToken
     */
    public Observable<LoginData> refreshToken(String refreshCode) {
        return mAccountApi.refreshToken(
                Constants.APP_KEY,
                Constants.APP_SECRET,
                REQUEST_REDIRECT_URI_OFFICIAL,
                REQUEST_GRANT_TYPE_REFRESH, refreshCode
        ).compose(flatResult());
    }

}
