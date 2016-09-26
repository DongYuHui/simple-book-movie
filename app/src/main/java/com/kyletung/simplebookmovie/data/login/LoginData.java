package com.kyletung.simplebookmovie.data.login;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/06 at 16:54<br>
 * <br>
 * FixMe
 */
public class LoginData {

    private String access_token;
    private String douban_user_id;
    private String refresh_token;
    private String douban_user_name;
    private int  expires_in;

    public String getDouban_user_name() {
        return douban_user_name;
    }

    public void setDouban_user_name(String douban_user_name) {
        this.douban_user_name = douban_user_name;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getDouban_user_id() {
        return douban_user_id;
    }

    public void setDouban_user_id(String douban_user_id) {
        this.douban_user_id = douban_user_id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "access_token='" + access_token + '\'' +
                ", douban_user_id='" + douban_user_id + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", douban_user_name='" + douban_user_name + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }

}
