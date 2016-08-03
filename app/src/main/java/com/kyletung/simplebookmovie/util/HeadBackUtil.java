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

    private static final int[] HEAD_BACK_IMAGES = {
            R.drawable.head_back_01,
            R.drawable.head_back_02,
            R.drawable.head_back_03,
            R.drawable.head_back_04,
            R.drawable.head_back_05
    };

    private Context mContext;

    private SPUtil mSPUtil;

    public HeadBackUtil(Context context) {
        this.mContext = context;
        mSPUtil = SPUtil.getInstance();
    }

    public int getImage() {
        int imageIndex = 0;
        int index = mSPUtil.read(mContext, "HeadBack", "index", -1);
        if (index != -1) {
            long time = mSPUtil.read(mContext, "HeadBack", "time", -1);
            if (System.currentTimeMillis() - time > (24 * 60 * 60 * 1000)) {
                index++;
                imageIndex = index;
                save(index);
            } else {
                imageIndex = index;
            }
        }
        return HEAD_BACK_IMAGES[imageIndex % 5];
    }

    private void save(int index) {
        mSPUtil.save(mContext, "HeadBack", "index", index);
        mSPUtil.save(mContext, "HeadBack", "time", System.currentTimeMillis());
    }

}
