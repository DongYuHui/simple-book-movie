package com.kyletung.commonlib.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/17 at 10:42<br>
 * 软键盘相关工具类
 */
public class KeyboardUtil {

    /**
     * 显示软键盘
     *
     * @param context  Context
     * @param editText EditText
     */
    public static void showKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.showSoftInput(editText, 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param context  Context
     * @param editText EditText
     */
    public static void hideKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        System.out.println("testtest hide keyboard");
    }

}
