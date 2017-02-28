package com.kyletung.simplebookmovie.data.book;

import java.io.Serializable;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:32<br>
 * <br>
 * FixMe
 */
public class Series implements Serializable {

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
