package com.kyletung.simplebookmovie.model.search;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.search.ISearchMovieView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 20:47<br>
 * <br>
 * FixMe
 */
public class SearchMovieModel extends BaseModel {

    private ISearchMovieView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public SearchMovieModel(Context context) {
        super(context);
    }

    public void setInterface(ISearchMovieView view) {
        mView = view;
    }

    public void search(String content) {

        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("q", content);
        params.put("start", String.valueOf(0));
        params.put("count", String.valueOf(20));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl("/movie/search", params), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieTopData data = getJsonUtil().fromJson(result, MovieTopData.class);
                    mView.onMovieSuccess(data.getSubjects());
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onMovieError(error);
                }
            }
        });

    }

    public void getMore(String content, int start) {

        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("q", content);
        params.put("start", String.valueOf(0));
        params.put("count", String.valueOf(start));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl("/movie/search", params), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieTopData data = getJsonUtil().fromJson(result, MovieTopData.class);
                    mView.onMoreSuccess(data.getSubjects());
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onMoreError(error);
                }
            }
        });

    }

}
