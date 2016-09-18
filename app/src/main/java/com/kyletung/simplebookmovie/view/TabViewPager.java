package com.kyletung.simplebookmovie.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/30 at 22:55<br>
 * <br>
 * Bottom Tab ViewPager
 * 当采用底部栏进行页面切换时，官方不建议手势滑动，所以拦截手势
 */
public class TabViewPager extends ViewPager {

    // ViewPager 是否能手势滑动
    private boolean mCanSwipe = false;

    public TabViewPager(Context context) {
        super(context);
    }

    public TabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否可手势滑动
     *
     * @param enabled 是否可滑动
     */
    public void setSwipeEnabled(boolean enabled) {
        this.mCanSwipe = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mCanSwipe && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mCanSwipe && super.onTouchEvent(ev);
    }

}
