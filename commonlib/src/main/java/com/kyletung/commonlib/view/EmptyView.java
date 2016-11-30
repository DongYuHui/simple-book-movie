package com.kyletung.commonlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyletung.commonlib.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/17 at 14:55<br>
 * 页面空白时显示的提示控件
 */
public class EmptyView extends LinearLayout {

    private int mEmptyImage;
    private String mEmptyText;

    private ImageView mImage;
    private TextView mText;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.EmptyView);
        mEmptyImage = typedArray.getResourceId(R.styleable.EmptyView_emptyImage, -1);
        mEmptyText = typedArray.getString(R.styleable.EmptyView_emptyText);
        typedArray.recycle();
    }

    private void initView() {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        mImage = new ImageView(getContext());
        mImage.setImageResource(mEmptyImage);
        LayoutParams imageParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mImage, imageParams);
        mText = new TextView(getContext());
        mText.setText(mEmptyText);
        LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mText, textParams);
        setOnClickListener(v -> {
        });
    }

    /**
     * 设置图片
     *
     * @param resource 图片资源
     */
    public void setImage(int resource) {
        mImage.setImageResource(resource);
    }

    /**
     * 设置文字
     *
     * @param content 文字
     */
    public void setText(String content) {
        mText.setText(content);
    }

}
