package com.kyletung.simplebookmovie.view;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2017/3/16 at 15:39<br>
 * 能够实现两个子 View 左右切换的效果
 */
public class SwitchLayout extends ViewGroup {

    private float mSwitchSlopLeft = 100;          // 左边滑动的判断范围
    private float mSwitchSlopRight = 100;         // 右边滑动的判断范围
    private boolean mSwitchEnableLeft = true;      // 左边滑动是否启用
    private boolean mSwitchEnableRight = true;     // 右边滑动是否启动

    private Scroller mScroller;

    private float mMoveRatio = 0.7F;        // 滑动的阻力系数

    private Type mNowType = Type.NONE;      // 当前模式，是在往右划还是往左滑
    private Type mNowStatus = Type.RIGHT;   // 当前位于左侧的控件还是右侧的控件

    private View mToggle;                   // 默认该布局的第三个子控件是切换控件，往往是一个小图标，点击之后能够使得当前页面进行左右切换
    private int mToggleMarginTop;           // 切换控件距离顶部的距离

    public SwitchLayout(Context context) {
        this(context, null);
    }

    public SwitchLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext(), new DecelerateInterpolator());
    }

    private float mToggleDownY;             // 切换控件按下时的 Y 坐标
    private long mToggleStartTime;          // 切换控件按下时的开始时间
    private boolean mToggleMove = false;    // 切换控件是否是可移动状态

    private void initToggleView() {
        if (getChildCount() > 3) return;
        // TODO: 2017/4/21 切换控件距离顶部的距离应该存储起来，初始化的时候读取
        mToggleMarginTop = 200;
        mToggle = getChildAt(2);
        mToggle.setOnClickListener(new OnClickListener() {
            @SuppressWarnings("UnnecessaryReturnStatement")
            @Override
            public void onClick(View v) {
                if (isPageInLeft()) {
                    scrollToRight();
                    return;
                }
                if (isPageInRight()) {
                    scrollToLeft();
                    return;
                }
            }
        });
        mToggle.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mToggleStartTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mToggleMove) {
                            float delta = event.getY() - mToggleDownY;
                            v.layout(-v.getMeasuredWidth() / 2, (int) (v.getY() + delta), v.getMeasuredWidth() / 2, (int) (v.getY() + delta + v.getMeasuredHeight()));
                        } else {
                            if (System.currentTimeMillis() - mToggleStartTime > 1000 && isTouchInView(event, v)) {
                                mToggleDownY = event.getY();
                                mToggleMove = true;
                                // TODO: 2017/4/21 change toggle view status
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        // TODO: 2017/4/21 这里还有上面的 ACTION_MOVE 应该防止控件被移出屏幕或者特定范围
                        if (mToggleMove) {
                            mToggleMarginTop = v.getTop();
                        }
                        // TODO: 2017/4/21 change toggle view status
                        mToggleMove = false;
                        mToggleDownY = -1;
                        if (System.currentTimeMillis() - mToggleStartTime < 500 && isTouchInView(event, v)) {
                            v.performClick();
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 设置左边滑动的判断范围
     *
     * @param slopLeft 范围大小
     */
    public void setSlopLeft(int slopLeft) {
        mSwitchSlopLeft = slopLeft;
    }

    /**
     * 设置右边滑动的判断范围
     *
     * @param slopRight 范围大小
     */
    public void setSlopRight(int slopRight) {
        mSwitchSlopRight = slopRight;
    }

    /**
     * 设置是否可以向左滑动
     *
     * @param switchLeftEnabled 是否可以滑动
     */
    public void setSwitchLeftEnabled(boolean switchLeftEnabled) {
        mSwitchEnableLeft = switchLeftEnabled;
    }

    /**
     * 设置是否可以向右滑动
     *
     * @param switchRightEnabled 是否可以滑动
     */
    public void setSwitchRightEnabled(boolean switchRightEnabled) {
        mSwitchEnableRight = switchRightEnabled;
    }

    /**
     * 设置滑动 Scroller 的 Interpolator
     *
     * @param interpolator Interpolator
     */
    public void setInterpolator(Interpolator interpolator) {
        mScroller = new Scroller(getContext(), interpolator);
    }

    /**
     * 设置滑动过程中的比率
     *
     * @param moveRatio 比率
     */
    public void setMoveRatio(float moveRatio) {
        mMoveRatio = moveRatio;
    }

    /**
     * 自动滑动到最左边
     */
    public void scrollToLeft() {
        if (mNowStatus == Type.LEFT) return;
        mNowStatus = Type.LEFT;
        if (mOnPageChangeListener != null) mOnPageChangeListener.onChange(mNowStatus);
        mScroller.startScroll(getScrollX(), 0, -getMeasuredWidth() - getScrollX(), 0);
        invalidate();
    }

    /**
     * 自动滑动到最右边
     */
    public void scrollToRight() {
        if (mNowStatus == Type.RIGHT) return;
        mNowStatus = Type.RIGHT;
        if (mOnPageChangeListener != null) mOnPageChangeListener.onChange(mNowStatus);
        mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
        invalidate();
    }

    /**
     * 当前页面是否是左边的控件
     *
     * @return 返回结果
     */
    public boolean isPageInLeft() {
        return mNowStatus == Type.LEFT;
    }

    /**
     * 当前页面是否是右边的控件
     *
     * @return 返回结果
     */
    public boolean isPageInRight() {
        return mNowStatus == Type.RIGHT;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount < 2 || childCount > 3) {
            throw new IllegalStateException("There must be only 2 or 3 child views.");
        }
        View viewFirst = getChildAt(0);
        viewFirst.layout(l, t, r, b);
        View viewSecond = getChildAt(1);
        viewSecond.layout(2 * l - r, t, l, b);
        if (childCount == 3) {
            if (mToggle == null) initToggleView();
            int viewThirdWidth = mToggle.getMeasuredWidth();
            int viewThirdHeight = mToggle.getMeasuredHeight();
            mToggle.layout(-viewThirdWidth / 2, mToggleMarginTop, viewThirdWidth / 2, mToggleMarginTop + viewThirdHeight);
        }
    }

    /**
     * 是否拦截当前触控事件序列
     */
    private boolean mTouchSwitch = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 最后判断触控点是否在切换控件上
                // 1. 不是，则由之后的逻辑判断是否拦截
                // 2. 是，则不拦截触控事件，全部交由该子控件处理
                if (isTouchInView(ev, mToggle)) {
                    intercept = false;
                    break;
                }
                // 触发拦截从左往右滑动的条件
                // 1. mSwitchEnableLeft 设置为 true
                // 2. 当前控件显示在右边的子控件
                // 3. 触控点落在最右边的范围内
                if (mSwitchEnableLeft && mNowStatus == Type.RIGHT && ev.getX() - getLeft() < mSwitchSlopLeft) {
                    intercept = true;
                    mNowType = Type.LEFT;
                    mTouchSwitch = true;
                }
                // 触发拦截从右往左滑动的条件
                // 1. mSwitchEnableRight 设置为 true
                // 2. 当前控件显示在左边的子控件
                // 3. 触控点落在最右边的范围内
                if (mSwitchEnableRight && mNowStatus == Type.LEFT && getRight() - ev.getX() < mSwitchSlopRight) {
                    intercept = true;
                    mNowType = Type.RIGHT;
                    mTouchSwitch = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mNowType = Type.NONE;
                mTouchSwitch = false;
                break;
        }
        return intercept;
    }

    /**
     * 判断触控事件是否在某控件上
     *
     * @param ev     触控事件
     * @param target 目标控件
     * @return 返回是否在该控件上
     */
    private boolean isTouchInView(MotionEvent ev, View target) {
        if (target == null) return false;
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        RectF rect = new RectF(location[0], location[1], location[0] + target.getMeasuredWidth(), location[1] + target.getMeasuredHeight());
        return rect.contains(ev.getRawX(), ev.getRawY());
    }

    /**
     * Action_Down 事件的 x 坐标
     */
    private float mDownX;

    /**
     * 开始滑动之前的提前量
     */
    private int mOffsetX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mTouchSwitch) return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mOffsetX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                handleMove((int) (-mOffsetX + (event.getX() - mDownX) * mMoveRatio));
                break;
            case MotionEvent.ACTION_UP:
                handleUp(event.getX());
                mTouchSwitch = false;
                break;
        }
        return true;
    }

    /**
     * 处理滑动过程
     *
     * @param delta 滑动到何处
     */
    private void handleMove(int delta) {
        scrollTo(-delta, 0);
    }

    /**
     * 处理触控事件抬起的结果
     *
     * @param pointX 手指抬起的 x 轴坐标
     */
    private void handleUp(float pointX) {
        if (mNowType == Type.LEFT) {
            // 从左往右
            boolean complete = (pointX * 2 - getLeft() - getRight()) > 0;
            if (complete) {
                mNowStatus = Type.LEFT;
                if (mOnPageChangeListener != null) mOnPageChangeListener.onChange(mNowStatus);
                mScroller.startScroll(getScrollX(), 0, -getMeasuredWidth() - getScrollX(), 0);
            } else {
                mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
            }
            invalidate();
        } else if (mNowType == Type.RIGHT) {
            // 从右往左
            boolean complete = (pointX * 2 - getLeft() - getRight()) < 0;
            if (complete) {
                mNowStatus = Type.RIGHT;
                if (mOnPageChangeListener != null) mOnPageChangeListener.onChange(mNowStatus);
                mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
            } else {
                mScroller.startScroll(getScrollX(), 0, -getMeasuredWidth() - getScrollX(), 0);
            }
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller == null) return;
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    public enum Type {
        NONE,       // 当前什么也没有
        LEFT,       // 用户从左往右滑动
        RIGHT       // 用户从右往左滑动
    }

    private OnPageChangeListener mOnPageChangeListener;

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public interface OnPageChangeListener {
        void onChange(Type type);
    }

}
