package com.kyletung.simplebookmovie.config;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/06/27 at 11:44<br>
 * <br>
 * 常量
 */
public class Constants {

    // Base Url
    public static final String BASE_URL = "https://api.douban.com/v2%s";

    // Application Info
    public static final String APP_KEY = "0208376c6d519a130618a64547f4ce39";
    public static final String APP_SECRET = "7e9cc2276a2ae982";

    // OAuth2 Login
    public static final String OAUTH_CODE = "https://www.douban.com/service/auth2/auth?client_id=%s&redirect_uri=http://www.kyletung.com&response_type=code&scope=book_basic_r,book_basic_w,douban_basic_common,movie_basic_r";
    public static final String OAUTH_TOKEN = "https://www.douban.com/service/auth2/token?client_id=%s&client_secret=%s&redirect_uri=http://www.kyletung.com&grant_type=authorization_code&code=%s";

    // SharedPreference Name
    public static final String SP_LOGIN_INFO = "LoginInfo";

    // Search Type
    public static final int SEARCH_TYPE_BOOK = 1;
    public static final int SEARCH_TYPE_MOVIE = 2;

    // Login RequestCode
    public static final int REQUEST_LOGIN = 123;

}
