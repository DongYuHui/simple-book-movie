package com.kyletung.simplebookmovie.ui;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.LoginData;
import com.kyletung.simplebookmovie.model.LoginModel;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.UserInfoUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 15:36<br>
 * <br>
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    private LoginModel mLoginModel;
    private UserInfoUtil mUserInfoUtil;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    @SuppressWarnings("setJavaScriptEnabled")
    protected void init() {
        // init model and util
        mLoginModel = new LoginModel(this);
        mLoginModel.setInterface(this);
        mUserInfoUtil = new UserInfoUtil(this);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.contains("www.kyletung.com/?code=")) {
                    showProgress("登陆中，请稍后", false, null);
                    String authorizationCode = url.substring(url.indexOf("=") + 1);
                    mLoginModel.getLoginInfo(authorizationCode);
                }
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

        });
        webView.loadUrl(String.format(Constants.OAUTH_CODE, Constants.APP_KEY));
    }

    @Override
    public void onLoginSuccess(LoginData data) {
        mUserInfoUtil.save(data.getAccess_token(), data.getDouban_user_id(), data.getRefresh_token());
        cancelProgress();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onLoginError(String error) {
        cancelProgress();
        BaseToast.toast(this, "登录失败：" + error);
    }

}
