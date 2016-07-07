package com.kyletung.simplebookmovie.model;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.LoginData;
import com.kyletung.simplebookmovie.ui.ILoginView;
import com.kyletung.simplebookmovie.util.VolleyUtil;

import org.json.JSONObject;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 16:47<br>
 * <br>
 * FixMe
 */
public class LoginModel extends BaseModel {

    private ILoginView mView;

    private VolleyUtil mVolleyUtil;

    public LoginModel(Activity activity) {
        super(activity);
        mVolleyUtil = VolleyUtil.getInstance();
    }

    public LoginModel(Fragment fragment) {
        super(fragment);
    }

    public void setInterface(ILoginView view) {
        this.mView = view;
    }

    public void getLoginInfo(String authorizationCode) {
//        getHttpUtil().postAsyn(
//                getActivity(),
//                String.format(Constants.OAUTH_TOKEN, Constants.APP_KEY, Constants.APP_SECRET, authorizationCode),
//                null,
//                new HttpUtil.OnResultListener() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("login success: " + result);
//                        if (mView != null) {
//                            LoginData data = getJsonUtil().fromJson(result, LoginData.class);
//                            mView.onLoginSuccess(data);
//                        }
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        System.out.println("login error: " + error);
//                        if (mView != null) {
//                            mView.onLoginError(error);
//                        }
//                    }
//                });
        mVolleyUtil.postJson(getActivity(), String.format(Constants.OAUTH_TOKEN, Constants.APP_KEY, Constants.APP_SECRET, authorizationCode), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("login success: " + response.toString());
                LoginData data = getJsonUtil().fromJson(response.toString(), LoginData.class);
                mView.onLoginSuccess(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("login error: " + error.toString()
                );
                mView.onLoginError(error.getMessage());
            }
        }, null);
    }

}
