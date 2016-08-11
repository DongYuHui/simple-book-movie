package com.kyletung.simplebookmovie.event;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/07 at 10:09<br>
 * <br>
 * 定义消息的基础，项目中的所有消息都应该使用这个类
 */
public class BaseEvent {

    /**
     * 用于表示事件的类别
     */
    private int what;

    /**
     * 用于表示事件的具体识别码
     */
    private int code;

    /**
     * 用于表示事件体
     */
    private Object object;

    public BaseEvent(int what, int code) {
        this.what = what;
        this.code = code;
    }

    public BaseEvent(int what, int code, Object object) {
        this.what = what;
        this.code = code;
        this.object = object;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
