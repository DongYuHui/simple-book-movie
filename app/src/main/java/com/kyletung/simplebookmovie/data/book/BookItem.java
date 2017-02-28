package com.kyletung.simplebookmovie.data.book;

import java.io.Serializable;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:27<br>
 * <br>
 * FixMe
 */
public class BookItem implements Serializable {

    private String status;
    private String updated;
    private String user_id;
    private String book_id;
    private long id;

    private BookSubject book;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookSubject getBook() {
        return book;
    }

    public void setBook(BookSubject book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "status='" + status + '\'' +
                ", updated='" + updated + '\'' +
                ", user_id='" + user_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", id=" + id +
                ", book=" + book +
                '}';
    }
}
