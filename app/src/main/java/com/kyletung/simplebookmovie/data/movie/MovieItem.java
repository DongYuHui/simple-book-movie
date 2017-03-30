package com.kyletung.simplebookmovie.data.movie;

import java.io.Serializable;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 17:36<br>
 * <br>
 * 电影列表单项的实体类
 */
public class MovieItem implements Serializable {

    private int box;
    private int rank;
    private MovieSubject subject;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public MovieSubject getSubject() {
        return subject;
    }

    public void setSubject(MovieSubject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "box=" + box +
                ", rank=" + rank +
                ", subject=" + subject +
                '}';
    }

}
