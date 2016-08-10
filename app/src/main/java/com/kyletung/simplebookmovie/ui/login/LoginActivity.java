package com.kyletung.simplebookmovie.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.model.login.LoginModel;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.UserInfoUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/08 at 22:23<br>
 * <br>
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.login_account)
    EditText mLoginAccount;
    @BindView(R.id.login_password)
    EditText mLoginPassword;
    @BindView(R.id.login_button)
    Button mLoginButton;

    private LoginModel mLoginModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        // 判断系统版本，当大于等于 KitKat 时，设置状态栏与导航栏为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
        }
        mLoginModel = new LoginModel(this, this);
        // set listener
        setListener();
    }

    private void setListener() {
        mLoginAccount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInfo();
            }

        });
        mLoginPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInfo();
            }

        });
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.login_button)
    public void loginButton(Button button) {
        showProgress("登录中，请稍后", false, null);
        String account = mLoginAccount.getText().toString();
        String password = mLoginPassword.getText().toString();
        mLoginModel.login(account, password);
    }

    private void checkInfo() {
        if (TextUtils.isEmpty(mLoginAccount.getText())) {
            mLoginButton.setEnabled(false);
            return;
        }
        if (TextUtils.isEmpty(mLoginPassword.getText())) {
            mLoginButton.setEnabled(false);
            return;
        }
        mLoginButton.setEnabled(true);
    }

    @Override
    public void onLoginSuccess() {
        stopProgress();
        // TODO: 2016/08/10 Login Success
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        String userId = new UserInfoUtil(this).readUserId();
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    public void onLoginError(String error) {
        stopProgress();
        // TODO: 2016/08/10 Login Error
        BaseToast.toast(this, "登录失败：" + error);
    }

}
