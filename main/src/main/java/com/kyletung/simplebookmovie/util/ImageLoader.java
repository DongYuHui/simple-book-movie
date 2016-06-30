package com.kyletung.simplebookmovie.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kyletung.simplebookmovie.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/27 at 10:51<br>
 * <br>
 * 包装 Glide 图片加载，方便统一管理及后续扩展
 */
public class ImageLoader {

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param view    控件
     * @param url     地址
     */
    public static void load(Context context, ImageView view, String url) {
        Glide.with(context).load(url).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

    /**
     * 加载图片
     *
     * @param context  上下文
     * @param view     控件
     * @param resource 资源
     */
    public static void load(Context context, ImageView view, int resource) {
        Glide.with(context).load(resource).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

    /**
     * 加载图片
     *
     * @param activity Activity
     * @param view     控件
     * @param url      地址
     */
    public static void load(Activity activity, ImageView view, String url) {
        Glide.with(activity).load(url).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

    /**
     * 加载图片
     *
     * @param activity Activity
     * @param view     控件
     * @param resource 资源
     */
    public static void load(Activity activity, ImageView view, int resource) {
        Glide.with(activity).load(resource).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

    /**
     * 加载图片
     *
     * @param fragment Fragment
     * @param view     控件
     * @param url      地址
     */
    public static void load(Fragment fragment, ImageView view, String url) {
        Glide.with(fragment).load(url).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

    /**
     * 加载图片
     *
     * @param fragment Fragment
     * @param view     控件
     * @param resource 资源
     */
    public static void load(Fragment fragment, ImageView view, int resource) {
        Glide.with(fragment).load(resource).placeholder(R.drawable.image_load_progress).error(R.drawable.image_load_error).into(view);
    }

}
