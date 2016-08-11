package com.kyletung.simplebookmovie.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/3/7.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取 Fragment 布局 Resource
     *
     * @return 返回布局 ID
     */
    protected abstract int setContentLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (mView == null) {
            mView = inflater.inflate(setContentLayout(), container, false);
            ButterKnife.bind(this, mView);
            initView(mView);
        }
        return mView;
    }

    /**
     * 由子类初始化视图
     *
     * @param view View
     */
    protected abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
