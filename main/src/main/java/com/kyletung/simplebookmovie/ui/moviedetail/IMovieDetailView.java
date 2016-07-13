package com.kyletung.simplebookmovie.ui.moviedetail;

import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 22:14<br>
 * <br>
 * FixMe
 */
public interface IMovieDetailView {

    void onGetDataSuccess(MovieDetailData data);

    void onGetDataError(String error);

}
