package com.kyletung.simplebookmovie.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;

import net.qiujuer.genius.blur.StackBlur;

import java.util.concurrent.ExecutionException;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/04 at 21:40<br>
 * <br>
 * 模糊工具类
 */
public class BlurUtil {

    /**
     * 从 Url 获取 Bitmap 并模糊化处理
     *
     * @param context     Context
     * @param url         资源
     * @param scaleWidth  缩放宽度
     * @param scaleHeight 缩放高度
     * @param radius      模糊单位，越大越模糊
     * @return 返回模糊后的 Bitmap
     */
    public static Bitmap blurFromUrl(Context context, String url, int scaleWidth, int scaleHeight, int radius) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context).load(url).asBitmap().override(scaleWidth, scaleHeight).into(scaleWidth, scaleHeight).get();
        } catch (InterruptedException | ExecutionException ignore) {
        }
        if (bitmap == null) return null;
        return StackBlur.blurNatively(bitmap, radius, true);
    }

}
