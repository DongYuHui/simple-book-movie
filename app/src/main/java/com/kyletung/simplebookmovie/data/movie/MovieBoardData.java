package com.kyletung.simplebookmovie.data.movie;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 18:08<br>
 * <br>
 * FixMe
 */
public class MovieBoardData {

    private String date;
    private String title;
    private ArrayList<MovieItem> subjects;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<MovieItem> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<MovieItem> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "MovieBoardData{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

}
