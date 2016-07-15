package com.kyletung.simplebookmovie.ui.search;

import com.kyletung.simplebookmovie.data.book.BookSubject;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 20:51<br>
 * <br>
 * FixMe
 */
public interface ISearchBookView {

    void onBookSuccess(ArrayList<BookSubject> list);

    void onBookError(String error);

    void onMoreSuccess(ArrayList<BookSubject> list);

    void onMoreError(String error);

}
