package com.kyletung.simplebookmovie.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;

import com.kyletung.commonlib.utils.ImageLoader;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/15 at 10:20<br>
 * 模仿 Zaker 封面效果的控件
 */
public class CoverView extends FrameLayout {

    private ImageView mImage;

    private Scroller mScroller;

    private Status mStatus = Status.NONE;

    private OnSlideListener mOnSlideListener;
    private OnReturnListener mOnReturnListener;

    public CoverView(@NonNull Context context) {
        this(context, null);
    }

    public CoverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoverView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mImage = new ImageView(getContext());
        mImage.setBackgroundColor(Color.WHITE);
        mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mImage, params);
    }

    /**
     * 设置图片网络地址
     *
     * @param url 网络地址
     */
    private void setImage(String url) {
        ImageLoader.load(getContext(), mImage, url);
    }

    /**
     * 设置图片资源
     *
     * @param src 资源
     */
    private void setImage(int src) {
        mImage.setImageResource(src);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return intercept;
    }

    private float mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaMove = event.getY() - mDownY;
                if (event.getY() > mDownY) deltaMove = 0;
                handleTouchMove((int) deltaMove);
                break;
            case MotionEvent.ACTION_UP:
                float deltaUp = event.getY() - mDownY;
                if (event.getY() > mDownY) deltaUp = 0;
                handleTouchUp((int) deltaUp);
                break;
        }
        return true;
    }

    /**
     * 处理触控中手指移动的动作
     *
     * @param delta 按下点与移动点的距离
     */
    private void handleTouchMove(int delta) {
        scrollTo(0, -delta);
    }

    /**
     * 处理触控中手指抬起的动作
     *
     * @param delta 按下点与抬起点的距离
     */
    private void handleTouchUp(int delta) {
        int abs = Math.abs(delta);
        if (abs > getMeasuredHeight() / 3) {
            mStatus = Status.SLIDE;
            // 向上滑出
            mScroller = new Scroller(getContext(), new AccelerateInterpolator());
            mScroller.startScroll(0, getScrollY(), 0, getMeasuredHeight() - Math.abs(getScrollY()), 300);
            invalidate();
        } else {
            mStatus = Status.BACK;
            // 向下还原
            mScroller = new Scroller(getContext(), new BounceInterpolator());
            mScroller.startScroll(0, getScrollY(), 0, -getScrollY(), 500);
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller != null && mScroller.computeScrollOffset()) {
            if (!mScroller.isFinished()) {
                scrollTo(0, mScroller.getCurrY());
                invalidate();
            } else {
                switch (mStatus) {
                    case SLIDE:
                        if (mOnSlideListener != null) mOnSlideListener.onSlideOut();
                        break;
                    case BACK:
                        if (mOnReturnListener != null) mOnReturnListener.onReturn();
                        break;
                }
                mStatus = Status.NONE;
            }
        }
    }

    /**
     * 设置图片滑出页面的监听回调
     *
     * @param onSlideListener 接口实现
     */
    public void setOnSlideListener(OnSlideListener onSlideListener) {
        mOnSlideListener = onSlideListener;
    }

    /**
     * 设置图片滑动到原位置的监听回调
     *
     * @param onReturnListener 接口实现
     */
    public void setOnReturnListener(OnReturnListener onReturnListener) {
        mOnReturnListener = onReturnListener;
    }

    public interface OnSlideListener {
        void onSlideOut();
    }

    public interface OnReturnListener {
        void onReturn();
    }

    private enum Status {
        NONE,
        SLIDE,
        BACK
    }

}
