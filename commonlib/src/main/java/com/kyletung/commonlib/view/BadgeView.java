package com.kyletung.commonlib.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.kyletung.commonlib.R;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/23.
 * 消息数量指示器
 */
public class BadgeView extends TextView {

    public static final int SIZE_SMALL = 0;
    public static final int SIZE_NORMAL = 1;
    public static final int SIZE_LARGE = 2;

    private int mNumber = 0; // 未读消息数量

    private boolean mShowNumber = true; // 显示样式是否为数字，亦或是单纯红点

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setGravity(Gravity.CENTER);
        setTextColor(Color.WHITE);
        setSize(SIZE_NORMAL);
        post(() -> setNumber(mNumber));
    }

    /**
     * 设置显示尺寸，默认有三种
     *
     * @param size 尺寸
     */
    public void setSize(int size) {
        switch (size) {
            case SIZE_SMALL:
                setMinWidth(dip2px(12));
                setMinHeight(dip2px(12));
                setBackgroundResource(R.drawable.badge_view_background_small);
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                break;
            case SIZE_NORMAL:
                setMinWidth(dip2px(15));
                setMinHeight(dip2px(15));
                setBackgroundResource(R.drawable.badge_view_background);
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                break;
            case SIZE_LARGE:
                setMinWidth(dip2px(17));
                setMinHeight(dip2px(17));
                setBackgroundResource(R.drawable.badge_view_background);
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                break;
        }
    }

    /**
     * 设置未读消息数量
     *
     * @param number 消息数量，大于零显示数量，小于等于零隐藏
     */
    public void setNumber(int number) {
        if (number > 0) {
            mNumber = number;
            setVisibility(VISIBLE);
        } else {
            mNumber = 0;
            setVisibility(GONE);
        }
        setText(mShowNumber ? String.valueOf(number) : "");
    }

    /**
     * 数量加一
     */
    public void addNumber() {
        mNumber++;
        setNumber(mNumber);
    }

    /**
     * 数量减一
     */
    public void cutNumber() {
        if (mNumber > 0) {
            mNumber--;
            setNumber(mNumber);
        }
    }

    /**
     * 获取当前的未读消息数量
     *
     * @return 返回未读消息数量
     */
    public int getNumber() {
        return mNumber;
    }

    /**
     * 设置样式是否为数字或者只有红点
     *
     * @param showNumber 是否显示数字
     */
    public void setShowNumber(boolean showNumber) {
        mShowNumber = showNumber;
        setNumber(mNumber);
    }

    /**
     * 私有方法，dp 转 px
     *
     * @param dipValue dp 值
     * @return 返回 px 值
     */
    private int dip2px(float dipValue) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        final float scale = dm.density;
        return (int) (dipValue * scale + 0.5f);
    }

}
