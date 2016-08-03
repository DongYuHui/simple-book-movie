package com.kyletung.simplebookmovie.ui.book;

import com.kyletung.simplebookmovie.data.book.BookItem;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 20:03<br>
 * <br>
 * FixMe
 */
public interface IBookView {

    void onDataSuccess(ArrayList<BookItem> list);

    void onDataError(String error);

    void onMoreSuccess(ArrayList<BookItem> list);

    void onMoreError(String error);

}
