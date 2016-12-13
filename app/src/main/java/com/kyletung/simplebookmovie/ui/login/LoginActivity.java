package com.kyletung.simplebookmovie.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.commonlib.utils.ToastUtil;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.client.request.AccountClient;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
import com.kyletung.simplebookmovie.utils.UserInfoUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/08 at 22:23<br>
 * <br>
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_account)
    EditText mLoginAccount;
    @BindView(R.id.login_password)
    EditText mLoginPassword;
    @BindView(R.id.login_button)
    Button mLoginButton;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        // 判断系统版本，当大于等于 KitKat 时，设置状态栏与导航栏为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
        }
    }

    @Override
    protected void business() {
        Observable<Boolean> rxAccount = RxTextView.textChanges(mLoginAccount).map(charSequence -> !TextUtils.isEmpty(charSequence));
        Observable<Boolean> rxPassword = RxTextView.textChanges(mLoginPassword).map(charSequence -> !TextUtils.isEmpty(charSequence));
        Observable.combineLatest(rxAccount, rxPassword, (aBoolean, aBoolean2) -> aBoolean && aBoolean2).subscribe(aBoolean -> {
            mLoginButton.setEnabled(aBoolean);
        });
        RxView.clicks(mLoginButton).throttleFirst(1, TimeUnit.SECONDS).subscribe(aVoid -> {
            showProgress("登录中，请稍后", false, null);
            String account = mLoginAccount.getText().toString();
            String password = mLoginPassword.getText().toString();
            getAuthorizationCode(account, password);
        });
    }

    public void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        String userId = new UserInfoUtil(this).readUserId();
        intent.putExtra("userId", userId);
        startActivity(intent);
        finish();
    }

    /**
     * 调用登录接口
     *
     * @param account  账户
     * @param password 密码
     */
    private void getAuthorizationCode(String account, String password) {
        AccountClient.getInstance().getCode(account, password).compose(responseBodyObservable -> {
            return Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    responseBodyObservable.subscribe(responseBody -> {
                        subscriber.onError(new Exception("登陆出错"));
                    }, throwable -> {
                        if (throwable instanceof HttpException) {
                            HttpException httpException = (HttpException) throwable;
                            try {
                                String result = httpException.response().errorBody().string();
                                if (result.contains("www.kyletung.com?code=")) {
                                    subscriber.onNext(result.substring(result.indexOf("=") + 1));
                                } else {
                                    subscriber.onError(throwable);
                                }
                            } catch (IOException e) {
                                subscriber.onError(e);
                            }
                        } else {
                            subscriber.onError(throwable);
                        }
                    });
                }
            });
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::getToken, throwable -> {
            stopProgress();
            ToastUtil.showToast(LoginActivity.this, "登陆失败：" + throwable.getMessage());
        });
    }

    /**
     * 获取 Token
     *
     * @param code 上一步获取到的 authorization code
     */
    private void getToken(String code) {
        AccountClient.getInstance().getToken(code).subscribe(newSubscriber(loginData -> {
            stopProgress();
            new UserInfoUtil(LoginActivity.this).save(
                    loginData.getAccess_token(),
                    loginData.getDouban_user_id(),
                    loginData.getRefresh_token());
            onLoginSuccess();
        }));
    }

}
