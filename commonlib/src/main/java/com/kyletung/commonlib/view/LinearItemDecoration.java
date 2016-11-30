package com.kyletung.commonlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/1/13.
 * 设置 RecyclerView 的间隔
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int size;
    private int orientation;
    private Context context;

    private Paint mPaint;

    public LinearItemDecoration(Context context, int orientation) {
        this.context = context;
        this.orientation = orientation;
        initPaint();
        size = dip2px(context, 4);
    }

    public LinearItemDecoration(Context context, int orientation, int size) {
        this(context, orientation);
        this.size = dip2px(context, size);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, android.R.color.transparent));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public void setSize(int size) {
        this.size = dip2px(context, size);
        notify();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, size);
        } else {
            outRect.set(0, 0, size, 0);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + size;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + size;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * scale: DisplayMetrics类中属性density
     *
     * @param context  上下文
     * @param dipValue dp 值
     * @return px 值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
