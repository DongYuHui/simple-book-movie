package com.kyletung.simplebookmovie.utils;

import android.content.Context;

import com.kyletung.commonlib.utils.SPUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/4/25<br>
 * <br>
 * 用来存储读取一些项目的配置信息
 */
public class ConfigHandler {

    private static final String CONFIG = "project_config";

    private static final String ITEM_TOGGLE_POSITION = "item_toggle_position";

    private Context mContext;
    private SPUtil mSPUtil;

    public ConfigHandler(Context context) {
        mContext = context;
        mSPUtil = SPUtil.getInstance();
    }

    public void saveTogglePosition(int position) {
        mSPUtil.save(mContext, CONFIG, ITEM_TOGGLE_POSITION, position);
    }

    public int readTogglePosition() {
        return mSPUtil.read(mContext, CONFIG, ITEM_TOGGLE_POSITION, 200);
    }

}
