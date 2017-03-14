package com.kyletung.commonlib.view.android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/13 at 17:40<br>
 * FixMe
 */
public class BaseTextView extends android.support.v7.widget.AppCompatTextView {

    public BaseTextView(Context context) {
        super(context);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
