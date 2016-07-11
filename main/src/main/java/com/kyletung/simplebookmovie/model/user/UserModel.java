package com.kyletung.simplebookmovie.model.user;

import android.content.Context;

import com.kyletung.simplebookmovie.data.user.UserData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.user.IUserView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 15:02<br>
 * <br>
 * 关于获取用户信息的 Model
 */
public class UserModel extends BaseModel {

    private IUserView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public UserModel(Context context) {
        super(context);
    }

    public void setInterface(IUserView view) {
        mView = view;
    }

    public void getUserInfo(String userId) {
        getHttpUtil().get(getContext(), getUrlUtil().bindUrl(String.format("/user/%s", userId)), new HttpUtil.OnResultListener() {

            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    UserData data = getJsonUtil().fromJson(result, UserData.class);
                    mView.onGetInfoSuccess(data);
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onGetInfoError(error);
                }
            }

        });
    }

}
