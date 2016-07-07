package com.kyletung.simplebookmovie.ui;

import com.kyletung.simplebookmovie.data.LoginData;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 17:04<br>
 * <br>
 * FixMe
 */
public interface ILoginView {

    void onLoginSuccess(LoginData data);

    void onLoginError(String error);

}
