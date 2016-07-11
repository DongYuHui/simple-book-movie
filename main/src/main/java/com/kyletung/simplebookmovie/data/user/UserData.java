package com.kyletung.simplebookmovie.data.user;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 13:28<br>
 * <br>
 * FixMe
 */
public class UserData {

    private String loc_id;
    private String name;
    private String created;
    private boolean is_banned;
    private boolean is_suicide;
    private String loc_name;
    private String avatar;
    private String signature;
    private String uid;
    private String alt;
    private String desc;
    private String type;
    private String id;
    private String large_avatar;

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                "loc_id='" + loc_id + '\'' +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", is_banned=" + is_banned +
                ", is_suicide=" + is_suicide +
                ", loc_name='" + loc_name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", uid='" + uid + '\'' +
                ", alt='" + alt + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", large_avatar='" + large_avatar + '\'' +
                '}';
    }

}
