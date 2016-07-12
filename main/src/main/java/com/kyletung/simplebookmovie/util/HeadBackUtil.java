package com.kyletung.simplebookmovie.util;

import android.content.Context;

import com.kyletung.simplebookmovie.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/12 at 22:18<br>
 * <br>
 * 用来随机读取本地图片作为个人中心头像的背景图片
 */
public class HeadBackUtil {

    private static final int[] headBackImages = {
            R.drawable.head_back_01,
            R.drawable.head_back_02,
            R.drawable.head_back_03,
            R.drawable.head_back_04,
            R.drawable.head_back_05
    };

    private Context mContext;

    public HeadBackUtil(Context context) {
        this.mContext = context;
    }



}
