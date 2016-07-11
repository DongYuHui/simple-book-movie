package com.kyletung.simplebookmovie.ui.user;

import com.kyletung.simplebookmovie.data.user.UserData;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 15:04<br>
 * <br>
 * FixMe
 */
public interface IUserView {

    void onGetInfoSuccess(UserData info);

    void onGetInfoError(String error);

}
