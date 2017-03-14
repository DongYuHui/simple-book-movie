package com.kyletung.commonlib.view.android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/14 at 15:10<br>
 * FixMe
 */
public class BaseImageView extends android.support.v7.widget.AppCompatImageView {
    public BaseImageView(Context context) {
        super(context);
    }

    public BaseImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
