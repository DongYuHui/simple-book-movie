package com.kyletung.simplebookmovie.util;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.kyletung.simplebookmovie.config.Constants;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 17:09<br>
 * <br>
 * 用于处理用户信息的工具类
 */
public class UserInfoUtil {

    private SPUtil mSPUtil;

    private Context mContext;

    public UserInfoUtil(Context context) {
        mContext = context;
        mSPUtil = SPUtil.getInstance();
    }

    /**
     * 保存登陆信息
     *
     * @param access_token   AccessToken
     * @param douban_user_id UserId
     * @param refresh_token  RefreshToken
     */
    public void save(String access_token, String douban_user_id, String refresh_token) {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("access_token", access_token);
        map.put("user_id", douban_user_id);
        map.put("refresh_token", refresh_token);
        mSPUtil.save(mContext, Constants.SP_LOGIN_INFO, map);
    }

    /**
     * 读取用户 Id
     *
     * @return 返回用户 Id
     */
    public String readUserId() {
        return mSPUtil.read(mContext, Constants.SP_LOGIN_INFO, "user_id", "");
    }

}
