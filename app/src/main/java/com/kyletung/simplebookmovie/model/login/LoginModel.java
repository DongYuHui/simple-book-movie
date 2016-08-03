package com.kyletung.simplebookmovie.model.login;

import android.content.Context;

import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.login.ILoginView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 16:47<br>
 * <br>
 * 登录页面 Model
 */
public class LoginModel extends BaseModel {

    private ILoginView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public LoginModel(Context context) {
        super(context);
    }

    public void setInterface(ILoginView view) {
        this.mView = view;
    }

    /**
     * 获取登录信息
     * @param authorizationCode    授权码
     */
    public void getLoginInfo(String authorizationCode) {
        getHttpUtil().post(getContext(), String.format(Constants.OAUTH_TOKEN, Constants.APP_KEY, Constants.APP_SECRET, authorizationCode), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                LoginData data = getJsonUtil().fromJson(result, LoginData.class);
                mView.onLoginSuccess(data);
            }

            @Override
            public void onError(String error) {
                mView.onLoginError(error);
            }
        }, null);
    }

}
