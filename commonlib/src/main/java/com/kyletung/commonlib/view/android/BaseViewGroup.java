package com.kyletung.commonlib.view.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/14 at 15:12<br>
 * FixMe
 */
public abstract class BaseViewGroup extends ViewGroup implements ILoadView {

    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
