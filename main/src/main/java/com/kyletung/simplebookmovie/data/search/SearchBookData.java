package com.kyletung.simplebookmovie.data.search;

import com.kyletung.simplebookmovie.data.book.BookSubject;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 21:42<br>
 * <br>
 * FixMe
 */
public class SearchBookData {

    private int count;
    private int start;
    private int total;
    private ArrayList<BookSubject> books;

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

    public ArrayList<BookSubject> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookSubject> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "SearchBookData{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
