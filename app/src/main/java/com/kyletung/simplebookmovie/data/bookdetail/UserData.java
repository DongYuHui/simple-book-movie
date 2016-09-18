package com.kyletung.simplebookmovie.data.bookdetail;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 21:12<br>
 * <br>
 * FixMe
 */
public class UserData {

    private String name;
    private boolean is_banned;
    private boolean is_suicide;
    private String url;
    private String avatar;
    private String uid;
    private String alt;
    private String type;
    private String id;
    private String large_avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean is_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public boolean is_suicide() {
        return is_suicide;
    }

    public void setIs_suicide(boolean is_suicide) {
        this.is_suicide = is_suicide;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLarge_avatar() {
        return large_avatar;
    }

    public void setLarge_avatar(String large_avatar) {
        this.large_avatar = large_avatar;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", is_banned=" + is_banned +
                ", is_suicide=" + is_suicide +
                ", url='" + url + '\'' +
                ", avatar='" + avatar + '\'' +
                ", uid='" + uid + '\'' +
                ", alt='" + alt + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", large_avatar='" + large_avatar + '\'' +
                '}';
    }

}
