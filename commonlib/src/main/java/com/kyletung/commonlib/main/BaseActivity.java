package com.kyletung.commonlib.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kyletung.commonlib.R;
import com.kyletung.commonlib.http.HttpErrorHandler;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * <br>
 * 基础 Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    // Progress Dialog
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        initView();
        business();
    }

    /**
     * 获取布局文件，由子类实现
     *
     * @return 返回布局文件
     */
    protected abstract int getContentLayout();

    /**
     * 提供一个方法供子类实现控件及相关初始化
     */
    protected abstract void initView();

    /**
     * 提供一个方法供子类实现业务逻辑
     */
    protected abstract void business();

    /**
     * 设置 ToolBar
     *
     * @param title 标题
     * @param back  是否显示返回按钮
     */
    protected void setToolbar(String title, boolean back) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (back && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Init Progress Dialog
     *
     * @param msg              Message
     * @param cancelable       Is ProgressDialog Cancelable
     * @param onCancelListener 取消 ProgressDialog 的监听器
     */
    protected void showProgress(String msg, boolean cancelable, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(msg);
            return;
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(cancelable);
        if (onCancelListener != null) mProgressDialog.setOnCancelListener(onCancelListener);
        mProgressDialog.show();
    }

    /**
     * Dismiss Progress Dialog
     */
    protected void stopProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 统一对网络请求结果做处理
     *
     * @param action1 Action
     * @param <T>     请求的实体结果
     * @return 返回观察者
     */
    protected <T> Subscriber<T> newSubscriber(Action1<T> action1) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                HttpErrorHandler.handle(getApplicationContext(), e);
            }

            @Override
            public void onNext(T t) {
                action1.call(t);
            }

        };
    }

    /**
     * 统一对网络请求结果做处理
     *
     * @param action1 Action
     * @param error   Error Action
     * @param <T>     请求的实体结果
     * @return 返回观察者
     */
    protected <T> Subscriber<T> newSubscriber(Action1<T> action1, Action1<Throwable> error) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                HttpErrorHandler.handle(getApplicationContext(), e);
                error.call(e);
            }

            @Override
            public void onNext(T t) {
                action1.call(t);
            }
        };
    }

}
