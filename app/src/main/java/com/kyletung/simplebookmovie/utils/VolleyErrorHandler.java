package com.kyletung.simplebookmovie.utils;

import com.kyletung.commonlib.utils.JsonUtil;
import com.kyletung.simplebookmovie.BaseApplication;
import com.kyletung.simplebookmovie.client.request.AccountClient;
import com.kyletung.simplebookmovie.data.login.LoginData;

import rx.Observer;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/06 at 11:04<br>
 * <br>
 * 错误处理工具类
 * 已经遗弃，留在这里是保留其中的更新授权码，以供之后参考
 */
@Deprecated
public class VolleyErrorHandler {

//    private static final String REFRESH_URL = "https://www.douban.com/service/auth2/token?client_id=%s&client_secret=%s&redirect_uri=http://www.douban.com&grant_type=refresh_token&refresh_token=%s";

//    /**
//     * 处理网络错误
//     *
//     * @param error           错误内容
//     * @param onOauthListener 处理接听
//     */
//    @SuppressWarnings("ConstantConditions")
//    public static void handleError(VolleyError error, OnOauthListener onOauthListener) {
//        if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
//            try {
//                String result;
//                int errorCode = new JSONObject(new String(error.networkResponse.data)).getInt("code");
//                switch (errorCode) {
//                    case 106:
//                        result = "正在重新授权，请稍后";
//                        refreshToken(onOauthListener);
//                        break;
//                    case 111:
//                        result = "访问过于频繁，请稍后再试";
//                        break;
//                    case 112:
//                        result = "访问过于频繁，请稍后再试";
//                        break;
//                    case 121:
//                        result = "用户不存在";
//                        break;
//                    default:
//                        result = "未知错误";
//                        break;
//                }
//                BaseToast.toast(BaseApplication.getInstance(), result);
//            } catch (JSONException e) {
//                if (onOauthListener != null) onOauthListener.onOauthError("未知网络错误");
//            }
//        } else {
//            if (onOauthListener != null) {
//                if (error.networkResponse != null) {
//                    onOauthListener.onOauthError(new String(error.networkResponse.data));
//                } else {
//                    onOauthListener.onOauthError(error.getMessage());
//                }
//            }
//        }
//    }

    /**
     * 刷新 Token
     *
     * @param onOauthListener 错误监听
     */
    private static void refreshToken(final OnOauthListener onOauthListener) {
        String refreshToken = new UserInfoUtil(BaseApplication.getInstance()).readRefreshToken();
        AccountClient.getInstance().refreshToken(refreshToken).subscribe(new Observer<LoginData>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                if (onOauthListener != null) onOauthListener.onOauthError(e.getMessage());
            }

            @Override
            public void onNext(LoginData loginData) {
                new UserInfoUtil(BaseApplication.getInstance()).save(
                        loginData.getAccess_token(),
                        loginData.getDouban_user_id(),
                        loginData.getRefresh_token()
                );
                if (onOauthListener != null)
                    onOauthListener.onRefreshSuccess(JsonUtil.getInstance().toJson(loginData));
            }

        });
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
