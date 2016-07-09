package com.kyletung.simplebookmovie.model.movie;

import android.content.Context;

import com.kyletung.simplebookmovie.data.movie.MovieBoardData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.movie.IMovieBoardView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/04 at 18:03<br>
 * <br>
 * 北美电影排行榜的 Model
 */
public class MovieBoardModel extends BaseModel {

    private IMovieBoardView mView;

    public MovieBoardModel(Context context, IMovieBoardView view) {
        super(context);
        mView = view;
    }

    public void setDataInterface(IMovieBoardView view) {
        mView = view;
    }

    /**
     * 获取数据
     */
    public void getData() {
        getHttpUtil().get(getContext(), getUrlUtil().bindUrl("/movie/us_box"), new HttpUtil.OnResultListener() {

            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieBoardData data = getJsonUtil().fromJson(result, MovieBoardData.class);
                    mView.onDataSuccess(data.getSubjects());
                }
            }

            @Override
            public void onError(String error) {
                mView.onDataError(error);
            }

        });
    }

}