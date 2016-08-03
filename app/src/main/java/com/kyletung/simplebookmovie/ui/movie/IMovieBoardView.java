package com.kyletung.simplebookmovie.ui.movie;

import com.kyletung.simplebookmovie.data.movie.MovieItem;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/04 at 18:40<br>
 * <br>
 * 电影列表接口
 */
public interface IMovieBoardView {

    void onDataSuccess(ArrayList<MovieItem> list);

    void onDataError(String error);

}
