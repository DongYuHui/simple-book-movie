package com.kyletung.library;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/27.
 * 对于系统对话框的一些简单封装
 */

public class BaseDialog {

    private static String ACTION_NAME_POSITIVE;
    private static String ACTION_NAME_NEGATIVE;

    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;

    private boolean mAutoCancel = true; // 是否自动取消

    private BaseDialog(Context context) {
        ACTION_NAME_POSITIVE = context.getString(R.string.dialog_action_positive);
        ACTION_NAME_NEGATIVE = context.getString(R.string.dialog_action_negative);
        mBuilder = new AlertDialog.Builder(context);
    }

    /**
     * 生成对象
     *
     * @param context 上下文
     * @return 返回当前实例
     */
    public BaseDialog create(Context context) {
        return new BaseDialog(context);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return 返回当前实例
     */
    public BaseDialog setTitle(String title) {
        mBuilder.setTitle(title);
        return this;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     * @return 返回当前实例
     */
    public BaseDialog setContent(String content) {
        mBuilder.setMessage(content);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param onClickListener 按钮监听
     * @return 返回当前实例
     */
    public BaseDialog setPositive(@Nullable final OnClickListener onClickListener) {
        mBuilder.setPositiveButton(ACTION_NAME_POSITIVE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAutoCancel) dismiss();
                if (onClickListener != null) onClickListener.onClick(BaseDialog.this);
            }
        });
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param name            按钮名称
     * @param onClickListener 按钮监听
     * @return 返回当前实例
     */
    public BaseDialog setPositive(String name, @Nullable final OnClickListener onClickListener) {
        mBuilder.setPositiveButton(name, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAutoCancel) dismiss();
                if (onClickListener != null) onClickListener.onClick(BaseDialog.this);
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param onClickListener 按钮监听
     * @return 返回当前实例
     */
    public BaseDialog setNegative(@Nullable final OnClickListener onClickListener) {
        mBuilder.setNegativeButton(ACTION_NAME_NEGATIVE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAutoCancel) dismiss();
                if (onClickListener != null) onClickListener.onClick(BaseDialog.this);
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param name            按钮名称
     * @param onClickListener 按钮监听
     * @return 返回当前实例
     */
    public BaseDialog setNegative(String name, @Nullable final OnClickListener onClickListener) {
        mBuilder.setNegativeButton(name, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAutoCancel) dismiss();
                if (onClickListener != null) onClickListener.onClick(BaseDialog.this);
            }
        });
        return this;
    }

    /**
     * 设置单选
     *
     * @param list                  单选数组
     * @param checkedPosition       预选中项
     * @param onSingleClickListener 点击监听
     * @return 返回当前实例
     */
    public BaseDialog setSingleChioce(final String[] list, int checkedPosition, final OnSingleClickListener onSingleClickListener) {
        mBuilder.setSingleChoiceItems(list, checkedPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAutoCancel) dismiss();
                if (onSingleClickListener != null)
                    onSingleClickListener.onItemClick(BaseDialog.this, which, list[which]);
            }
        });
        return this;
    }

    /**
     * 设置自定义控件
     *
     * @param view 自定义控件
     * @return 返回当前实例
     */
    public BaseDialog setCustomView(View view) {
        mBuilder.setView(view);
        return this;
    }

    /**
     * 设置是否自动取消
     *
     * @param autoCancel 是否自动取消
     * @return 返回当前实例
     */
    public BaseDialog setAutoCancel(boolean autoCancel) {
        mAutoCancel = autoCancel;
        return this;
    }

    /**
     * 显示
     */
    public void show() {
        mDialog = mBuilder.create();
        mDialog.show();
    }

    /**
     * 取消对话框
     */
    public void dismiss() {
        if (mDialog == null || !mDialog.isShowing()) return;
        mDialog.dismiss();
    }

    /**
     * 用户点击的回调接口
     */
    public interface OnClickListener {
        /**
         * 用户点击的回调方法
         *
         * @param dialog 当前实例
         */
        void onClick(BaseDialog dialog);
    }

    /**
     * 用户点击单选的回调接口
     */
    public interface OnSingleClickListener {
        /**
         * 用户点击的回调接口
         *
         * @param dialog   当前实例
         * @param position 点中位置
         * @param content  点中内容
         */
        void onItemClick(BaseDialog dialog, int position, String content);
    }

}
