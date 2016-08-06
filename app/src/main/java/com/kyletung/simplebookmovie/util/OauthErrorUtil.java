package com.kyletung.simplebookmovie.util;

import com.android.volley.VolleyError;
import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.config.Constants;
import com.kyletung.simplebookmovie.data.login.LoginData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/06 at 11:04<br>
 * <br>
 * 错误处理工具类
 */
public class OauthErrorUtil {

    private static final String REFRESH_URL = "https://www.douban.com/service/auth2/token?client_id=%s&client_secret=%s&redirect_uri=http://www.douban.com&grant_type=refresh_token&refresh_token=%s";

    /**
     * 处理网络错误
     *
     * @param error           错误内容
     * @param onOauthListener 处理接听
     */
    @SuppressWarnings("ConstantConditions")
    public static void handleError(VolleyError error, OnOauthListener onOauthListener) {
        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
            try {
                String result;
                int errorCode = new JSONObject(new String(error.networkResponse.data)).getInt("code");
                switch (errorCode) {
                    case 106:
                        result = "正在重新授权，请稍后";
                        refreshToken(onOauthListener);
                        break;
                    case 111:
                        result = "访问过于频繁，请稍后再试";
                        break;
                    case 112:
                        result = "访问过于频繁，请稍后再试";
                        break;
                    case 121:
                        result = "用户不存在";
                        break;
                    default:
                        result = "未知错误";
                        break;
                }
                BaseToast.toast(BaseApplication.getInstance(), result);
            } catch (JSONException e) {
                if (onOauthListener != null) onOauthListener.onOauthError("未知网络错误");
            }
        } else {
            if (onOauthListener != null) {
                if (error.networkResponse != null) {
                    onOauthListener.onOauthError(new String(error.networkResponse.data));
                } else {
                    onOauthListener.onOauthError(error.getMessage());
                }
            }
        }
    }

    /**
     * 刷新 Token
     *
     * @param onOauthListener 错误监听
     */
    private static void refreshToken(final OnOauthListener onOauthListener) {
        String refreshToken = new UserInfoUtil(BaseApplication.getInstance()).readRefreshToken();
        String url = String.format(REFRESH_URL, Constants.APP_KEY, Constants.APP_SECRET, refreshToken);
        HttpUtil.getInstance().post(null, url, new HttpUtil.OnResultListener() {

            @Override
            public void onSuccess(String result) {
                LoginData data = JsonUtil.getInstance().fromJson(result, LoginData.class);
                new UserInfoUtil(BaseApplication.getInstance()).save(
                        data.getAccess_token(),
                        data.getDouban_user_id(),
                        data.getRefresh_token()
                );
                if (onOauthListener != null) onOauthListener.onRefreshSuccess(result);
            }

            @Override
            public void onError(String error) {
                if (onOauthListener != null) onOauthListener.onOauthError(error);
            }

        }, null);
    }

    /**
     * 错误返回接口
     */
    public interface OnOauthListener {

        /**
         * 刷新 Token 成功
         *
         * @param result 结果
         */
        void onRefreshSuccess(String result);

        /**
         * 刷新错误
         *
         * @param error 错误
         */
        void onOauthError(String error);

    }

}
