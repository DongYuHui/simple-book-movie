package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.data.user.UserData;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 用户资料相关
 */
public class AccountClient extends SimpleClient {

    private static final String REQUEST_REDIRECT_URI = "http://www.kyletung.com";
    private static final String REQUEST_GRANT_TYPE = "authorization_code";

    private AccountApi mAccountApi;

    private AccountClient() {
        super();
        mAccountApi = getRetrofit().create(AccountApi.class);
    }

    public static AccountClient getInstance() {
        return new AccountClient();
    }

    /**
     * 获取用户资料
     *
     * @param userId       用户 Id
     * @param responseImpl 返回接口实现
     */
    public void getUserData(String userId, IResponse<UserData> responseImpl) {
        mAccountApi.getUserData(userId).enqueue(newCallback(responseImpl));
    }

    public void getToken(String code, IResponse<LoginData> responseImpl) {
//        mAccountApi.getToken(Constants.APP_KEY, Constants.APP_SECRET, code).enqueue(newCallback(responseImpl));
        /*
        client_id={app_key}&client_secret={app_secret}&redirect_uri=http://www.kyletung.com&grant_type=authorization_code&code={code}
         */
        mAccountApi.getToken(Constants.APP_KEY, Constants.APP_SECRET, REQUEST_REDIRECT_URI, REQUEST_GRANT_TYPE, code).enqueue(newCallback(responseImpl));
    }

}
