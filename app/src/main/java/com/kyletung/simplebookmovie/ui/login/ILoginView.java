package com.kyletung.simplebookmovie.ui.login;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/10 at 20:39<br>
 * <br>
 * 登录接口
 */
public interface ILoginView {

    void onLoginSuccess();

    void onLoginError(String error);

}
