package com.kyletung.simplebookmovie.ui.movie;

import com.kyletung.simplebookmovie.data.movie.MovieSubject;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 18:37<br>
 * <br>
 * FixMe
 */
public interface IMovieTopView {

    void onDataSuccess(ArrayList<MovieSubject> list);

    void onDataError(String error);

    void onMoreSuccess(ArrayList<MovieSubject> list);

    void onMoreError(String error);

}
