package com.kyletung.simplebookmovie.ui.bookdetail;

import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.model.bookdetail.BookCollectionData;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 21:03<br>
 * <br>
 * FixMe
 */
public interface IBookDetailView {

    void onGetDataSuccess(BookDetailData data);

    void onGetDataError(String error);

    void onGetCollectionSuccess(BookCollectionData data);

    void onGetCollectionError(String error);

}
