package com.kyletung.commonlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.kyletung.commonlib.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/03/07 at 21:23<br>
 * 矩形布局控件，可以设置固定比例
 */
public class RectLayout extends RelativeLayout {

    /**
     * 自定义属性 orientation：代表是高度根据宽度而定，还是宽度根据高度而定
     */
    private int orientation;

    /**
     * 自定义属性 scaleWidth：代表宽度的比例
     */
    private int scaleWidth;

    /**
     * 自定义属性 scaleHeight：代表高度的比例
     */
    private int scaleHeight;

    public RectLayout(Context context) {
        this(context, null);
    }

    public RectLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectLayout);
        orientation = typedArray.getInt(R.styleable.RectLayout_scaleOrientation, 0);
        scaleWidth = typedArray.getInt(R.styleable.RectLayout_scaleWidth, 0);
        scaleHeight = typedArray.getInt(R.styleable.RectLayout_scaleHeight, 0);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        //如果长宽的比例中存在小于等于零的值，则默认不做任何比例调整
        if (scaleWidth > 0 && scaleHeight > 0) {
            if (orientation == 0) {
                // orientation 为 0 则代表适用于纵向的布局中，高度跟随宽度
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth() * scaleHeight / scaleWidth, MeasureSpec.EXACTLY);
            } else {
                // orientation 为 1 则代表适用于横向的布局中，宽度跟随高度
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() * scaleWidth / scaleHeight, MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

}
