package com.kyletung.simplebookmovie.client;

import com.kyletung.simplebookmovie.data.user.UserData;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/14.
 * 用户资料相关
 */
public class AccountClient extends SimpleClient {

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

}
