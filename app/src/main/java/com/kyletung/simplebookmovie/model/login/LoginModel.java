package com.kyletung.simplebookmovie.model.login;

import android.content.Context;
import android.provider.ContactsContract;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kyletung.simplebookmovie.client.AccountClient;
import com.kyletung.simplebookmovie.client.IResponse;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.login.LoginData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.login.ILoginView;
import com.kyletung.simplebookmovie.util.HttpUtil;
import com.kyletung.simplebookmovie.util.UserInfoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/07 at 18:24<br>
 * <br>
 * Login Model
 */
public class LoginModel extends BaseModel {

    private static final String CONFIRM = "授权";
    private static final String RESPONSE_TYPE = "code";
    private static final String REDIRECT_URL = "http://www.kyletung.com";

    private ILoginView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public LoginModel(Context context, ILoginView view) {
        super(context);
        this.mView = view;
    }

    /**
     * 调用登录接口
     *
     * @param account  账户
     * @param password 密码
     */
    public void login(String account, String password) {
        final Map<String, String> params = new HashMap<>();
        params.put("confirm", CONFIRM);
        params.put("redirect_url", REDIRECT_URL);
        params.put("client_id", Constants.APP_KEY);
        params.put("response_type", RESPONSE_TYPE);
        params.put("user_alias", account);
        params.put("user_passwd", password);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                String.format(Constants.OAUTH_CODE, Constants.APP_KEY),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // TODO: 2016/08/07 请求成功，但是这里不会被调用，登录成功会返回 302 代码，调用错误监听
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    String url = new String(error.networkResponse.data);
                    if (url.contains("www.kyletung.com?code=")) {
                        String authorizationCode = url.substring(url.indexOf("=") + 1);
                        getToken(authorizationCode);
                    } else {
                        mView.onLoginError("获取 AuthorizationCode 出错");
                    }
                } else {
                    mView.onLoginError("获取 AuthorizationCode 出错");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        getHttpUtil().getRequestQueue().add(stringRequest);
    }

    /**
     * 获取 Token
     *
     * @param code 上一步获取到的 authorization code
     */
    private void getToken(String code) {
        getHttpUtil().post(
                getContext(),
                String.format(Constants.OAUTH_TOKEN, Constants.APP_KEY, Constants.APP_SECRET, code),
                new HttpUtil.OnResultListener() {

                    @Override
                    public void onSuccess(String result) {
                        LoginData data = getJsonUtil().fromJson(result, LoginData.class);
                        new UserInfoUtil(getContext()).save(
                                data.getAccess_token(),
                                data.getDouban_user_id(),
                                data.getRefresh_token());
                        mView.onLoginSuccess();
                    }

                    @Override
                    public void onError(String error) {
                        mView.onLoginError(error);
                    }

                },
                null
        );
//        AccountClient.getInstance().getToken(code, new IResponse<LoginData>() {
//
//            @Override
//            public void onResponse(LoginData result) {
//                new UserInfoUtil(getContext()).save(result.getAccess_token(), result.getDouban_user_id(), result.getRefresh_token());
//                mView.onLoginSuccess();
//            }
//
//            @Override
//            public void onError(int code, String reason) {
//                mView.onLoginError(reason);
//            }
//
//        });
    }

}
