package com.kyletung.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/10 at 9:41<br>
 * 权限工具类，目前只支持 Activity 中的单个权限请求
 */
public class PermissionUtil {

    private static final String DESC_TITLE = "权限提示";

    private static final int REQUEST_CODE = 777; // 默认请求码

    private Activity mActivity;

    private OnGrantedListener mOnGrantedListener;               // 用户授权的回调接口
    private OnDeniedForeverListener mOnDeniedForeverListener;   // 用户用于拒绝的回调接口
    private OnDeniedListener mOnDeniedListener;                 // 用户拒绝的回调接口

    /**
     * 请求单个权限
     *
     * @param activity   Activity
     * @param permission 权限
     * @param rationale  提示
     */
    public void checkPermission(Activity activity, String permission, String rationale) {
        if (PermissionChecker.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            mActivity = activity;
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showDialog(activity, DESC_TITLE, rationale, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE);
            }
        } else {
            if (mOnGrantedListener != null) mOnGrantedListener.onGranted();
        }
    }

    /**
     * 请求单个权限
     *
     * @param fragment   Fragment
     * @param permission 权限
     * @param rationale  提示
     */
    public void checkPermission(Fragment fragment, String permission, String rationale) {
        if (PermissionChecker.checkSelfPermission(fragment.getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            mActivity = fragment.getActivity();
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                showDialog(mActivity, DESC_TITLE, rationale, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.requestPermissions(new String[]{permission}, REQUEST_CODE);
                    }
                });
            } else {
                fragment.requestPermissions(new String[]{permission}, REQUEST_CODE);
            }
        } else {
            if (mOnGrantedListener != null) mOnGrantedListener.onGranted();
        }
    }

    /**
     * 请求一组权限
     *
     * @param activity    Activity
     * @param permissions 权限组
     * @param rationale   提示
     */
    public void checkPermissions(Activity activity, String[] permissions, String rationale) {
        List<String> permissionNot = new ArrayList<>();
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNot.add(permission);
            }
        }
        if (permissionNot.size() == 0) {
            if (mOnGrantedListener != null) mOnGrantedListener.onGranted();
            return;
        }
        mActivity = activity;
        boolean shouldShowDialog = false;
        for (String permission : permissionNot) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                shouldShowDialog = true;
                break;
            }
        }
        String[] permissionRequest = new String[permissionNot.size()];
        for (int i = 0; i < permissionNot.size(); i++) {
            permissionRequest[i] = permissionNot.get(i);
        }
        if (shouldShowDialog) {
            showDialog(activity, DESC_TITLE, rationale, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(activity, permissionRequest, REQUEST_CODE);
                }
            });
        } else {
            ActivityCompat.requestPermissions(activity, permissionRequest, REQUEST_CODE);
        }
    }

    /**
     * 请求一组权限
     *
     * @param fragment    Fragment
     * @param permissions 权限组
     * @param rationale   提示
     */
    public void checkPermissions(Fragment fragment, String[] permissions, String rationale) {
        List<String> permissionNot = new ArrayList<>();
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(fragment.getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNot.add(permission);
            }
        }
        if (permissionNot.size() == 0) {
            if (mOnGrantedListener != null) mOnGrantedListener.onGranted();
            return;
        }
        mActivity = fragment.getActivity();
        boolean shouldShowDialog = false;
        for (String permission : permissionNot) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                shouldShowDialog = true;
                break;
            }
        }
        String[] permissionRequest = new String[permissionNot.size()];
        for (int i = 0; i < permissionNot.size(); i++) {
            permissionRequest[i] = permissionNot.get(i);
        }
        if (shouldShowDialog) {
            showDialog(mActivity, DESC_TITLE, rationale, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fragment.requestPermissions(permissionRequest, REQUEST_CODE);
                }
            });
        } else {
            fragment.requestPermissions(permissionRequest, REQUEST_CODE);
        }
    }

    /**
     * 请求的结果回调
     *
     * @param requestCode  请求码
     * @param permissions  请求的权限
     * @param grantResults 请求的权限相对应的结果
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != REQUEST_CODE) return;
        boolean allowedAll = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allowedAll = false;
                break;
            }
        }
        if (allowedAll) {
            if (mOnGrantedListener != null) mOnGrantedListener.onGranted();
        } else {
            boolean shouldShow = false;
            for (String permission : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                    shouldShow = true;
                    break;
                }
            }
            if (shouldShow) {
                if (mOnDeniedListener != null) mOnDeniedListener.onDenied();
            } else {
                if (mOnDeniedForeverListener != null) mOnDeniedForeverListener.onDeniedForever();
            }
        }
    }

    /**
     * 显示一个对话框
     *
     * @param context         Context
     * @param title           标题
     * @param content         内容
     * @param onClickListener 点击监听
     */
    private void showDialog(Context context, String title, String content, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setPositiveButton("确定", onClickListener);
        builder.create().show();
    }

    /**
     * 设置用户授权的回调接口
     *
     * @param onGrantedListener 接口实现
     */
    public void setOnGrantedListener(OnGrantedListener onGrantedListener) {
        mOnGrantedListener = onGrantedListener;
    }

    /**
     * 设置用户拒绝并不再提示的回调接口
     *
     * @param onDeniedForeverListener 接口实现
     */
    public void setOnDeniedForeverListener(OnDeniedForeverListener onDeniedForeverListener) {
        mOnDeniedForeverListener = onDeniedForeverListener;
    }

    /**
     * 设置用户拒绝的回调接口
     *
     * @param onDeniedListener 接口实现
     */
    public void setOnDeniedListener(OnDeniedListener onDeniedListener) {
        mOnDeniedListener = onDeniedListener;
    }

    /**
     * 用户授权的回调接口
     */
    public interface OnGrantedListener {
        void onGranted();
    }

    /**
     * 用户拒绝并勾选‘不在提示’的回调接口
     */
    public interface OnDeniedForeverListener {
        void onDeniedForever();
    }

    /**
     * 用户拒绝的回调接口
     */
    public interface OnDeniedListener {
        void onDenied();
    }

}
