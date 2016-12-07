package com.kyletung.commonlib.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtil {

    /**
     * handler to show toasts safely
     */
    private static Handler mHandler = null;

    private static Toast toast = null;

    public static void showToast(final Context mContext, final int resId) {
        sharedHandler(mContext).post(() -> {
            if (toast != null) {
                toast.setText(resId);
                toast.setDuration(Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(mContext.getApplicationContext(), resId, Toast.LENGTH_SHORT);
            }
            toast.show();
        });
    }

    public static void showToast(final Context mContext, final String text) {
        sharedHandler(mContext).post(() -> {
            if (toast != null) {
                toast.setText(text);
                toast.setDuration(Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_SHORT);
            }
            toast.show();
        });
    }

    public static void showLongToast(final Context mContext, final int resId) {
        sharedHandler(mContext).post(() -> {
            if (toast != null) {
                toast.setText(resId);
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(mContext.getApplicationContext(), resId, Toast.LENGTH_LONG);
            }
            toast.show();
        });
    }

    public static void showLongToast(final Context mContext, final String text) {
        sharedHandler(mContext).post(() -> {
            if (toast != null) {
                toast.setText(text);
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_LONG);
            }
            toast.show();
        });
    }

    private static Handler sharedHandler(Context context) {
        if (mHandler == null) {
            mHandler = new Handler(context.getMainLooper());
        }
        return mHandler;
    }

}
