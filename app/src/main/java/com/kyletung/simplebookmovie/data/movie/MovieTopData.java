package com.kyletung.simplebookmovie.data.movie;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 17:36<br>
 * <br>
 * FixMe
 */
public class MovieTopData {

    private int count;
    private int start;
    private int total;
    private String title;
    private ArrayList<MovieSubject> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<MovieSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<MovieSubject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "MovieTopData{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

}
