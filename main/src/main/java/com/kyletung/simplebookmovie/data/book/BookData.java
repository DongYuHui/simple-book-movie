package com.kyletung.simplebookmovie.data.book;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:26<br>
 * <br>
 * FixMe
 */
public class BookData {

    private int count;
    private int start;
    private int total;

    private ArrayList<BookItem> collections;

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

    public ArrayList<BookItem> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<BookItem> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", collections=" + collections +
                '}';
    }

}
