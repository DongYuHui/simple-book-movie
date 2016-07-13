package com.kyletung.simplebookmovie.model.bookdetail;

import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 21:10<br>
 * <br>
 * FixMe
 */
public class BookCollectionData {

    private String status;
    private String updated;
    private String user_id;
    private BookDetailData book;
    private UserData user;
    private String book_id;
    private long id;

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

    public BookDetailData getBook() {
        return book;
    }

    public void setBook(BookDetailData book) {
        this.book = book;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "BookCollectionData{" +
                "status='" + status + '\'' +
                ", updated='" + updated + '\'' +
                ", user_id='" + user_id + '\'' +
                ", book=" + book +
                ", user=" + user +
                ", book_id='" + book_id + '\'' +
                ", id=" + id +
                '}';
    }

}
