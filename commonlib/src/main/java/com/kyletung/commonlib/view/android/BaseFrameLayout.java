package com.kyletung.commonlib.view.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/13 at 17:37<br>
 * FixMe
 */
public class BaseFrameLayout extends FrameLayout implements ILoadView {

    /**
     * 有时候界面需要显示一个覆盖整个 ViewGroup 的加载动画控件，所以在基础控件里面封装好
     * 采用 Airbnb Lottie 库来显示动画
     */
    private LottieAnimationView mLoadView;

    public BaseFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLoad();
    }

    @Override
    public void initLoad() {
        mLoadView = new LottieAnimationView(getContext());
        mLoadView.setBackgroundColor(Color.WHITE);
        mLoadView.setAnimation(DEFAULT_LOAD_FILE);
        mLoadView.setVisibility(GONE);
        mLoadView.loop(true);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mLoadView, params);
    }

    @Override
    public void setLoadFile(String file) {
        if (mLoadView == null) return;
        mLoadView.setAnimation(file);
    }

    @Override
    public boolean isLoading() {
        return mLoadView != null && mLoadView.isAnimating();
    }

    @Override
    public void startLoad() {
        if (mLoadView == null) return;
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLoadView, "alpha", 0F, 1F);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLoadView.setVisibility(VISIBLE);
                mLoadView.bringToFront();
                mLoadView.playAnimation();
            }
        });
        animator.setDuration(300L);
        animator.start();
    }

    @Override
    public void stopLoad() {
        if (mLoadView == null) return;
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLoadView, "alpha", 1F, 0F);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoadView.cancelAnimation();
                mLoadView.setVisibility(GONE);
            }
        });
        animator.setDuration(300L);
        animator.start();
    }

    @Override
    public void onLoadError(String error) {
        // TODO: 2017/3/14 当加载过程出现错误的时候，这里应该显示错误信息
    }

    @Override
    public void destroyLoad() {
        if (mLoadView == null) return;
        mLoadView.cancelAnimation();
        removeView(mLoadView);
        mLoadView = null;
    }

    @Override
    public LottieAnimationView getLoadView() {
        return mLoadView;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroyLoad();
    }

}
