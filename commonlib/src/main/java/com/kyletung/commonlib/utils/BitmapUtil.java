package com.kyletung.commonlib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/22 at 15:39<br>
 * Bitmap 处理工具类
 */
public class BitmapUtil {

    /**
     * 压缩并保存图片
     *
     * @param input   源图片
     * @param output  输出图片
     * @param width   宽度限制
     * @param height  高度限制
     * @param quality 压缩质量
     * @return 返回是否压缩保存成功
     */
    public static boolean compressAndSaveBitmap(File input, File output, int width, int height, int quality) {
        Bitmap bitmap = getSmallBitmap(input.getAbsolutePath(), width, height);
        return saveBitmap(bitmap, output.getAbsolutePath(), quality);
    }

    /**
     * 压缩图片
     *
     * @param bitmap   Bitmap
     * @param filePath 保存的路径
     * @param quality  保存的质量
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean saveBitmap(Bitmap bitmap, String filePath, int quality) {
        boolean result = false;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            if (file.exists()) file.delete();
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            result = true;
        } catch (IOException ignored) {
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }

    /**
     * 获取压缩后的图片
     *
     * @param filePath 图片地址
     * @return 返回压缩后的图片
     */
    public static Bitmap getSmallBitmap(String filePath, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width, height);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 计算图片的缩放值
     *
     * @param options   Option
     * @param reqWidth  期望宽度
     * @param reqHeight 期望高度
     * @return 返回缩放值
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
