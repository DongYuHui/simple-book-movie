package com.kyletung.simplebookmovie.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/30 at 21:20<br>
 * <br>
 * BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    private View mView;

    // Progress Dialog
    protected ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getContentLayout(), container, false);
            ButterKnife.bind(this, mView);
            init(mView);
        }
        return mView;
    }

    /**
     * 获取布局文件，由子类实现
     *
     * @return 返回布局文件
     */
    protected abstract int getContentLayout();

    /**
     * 初始化页面
     *
     * @param view View
     */
    protected abstract void init(View view);

    /**
     * Init Progress Dialog
     *
     * @param msg              Message
     * @param cancelable       Is ProgressDialog Cancelable
     * @param onCancelListener 取消 ProgressDialog 的监听器
     */
    protected void showProgress(String msg, boolean cancelable, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(cancelable);
        if (onCancelListener != null) mProgressDialog.setOnCancelListener(onCancelListener);
        mProgressDialog.show();
    }

    /**
     * Dismiss Progress Dialog
     */
    protected void stopProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
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
    protected <T> Subscriber<T> newSubscriber(final Action1<T> action1) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
//                HttpErrorHandler.handle(getApplicationContext(), e);
            }

            @Override
            public void onNext(T t) {
                action1.call(t);
            }

        };
    }

    protected <T> Subscriber<T> newSubscriber(Action1<T> action1, Action1<Throwable> error) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                error.call(e);
            }

            @Override
            public void onNext(T t) {
                action1.call(t);
            }
        };
    }

}
