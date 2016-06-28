package com.kyletung.simplebookmovie.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/27 at 11:58<br>
 * <br>
 * 基础 Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        init();
    }

    /**
     * 获取布局文件，由子类实现
     *
     * @return 返回布局文件
     */
    protected abstract int getContentLayout();

    /**
     * 提供一个方法供子类实现
     */
    protected abstract void init();

}
