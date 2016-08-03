package com.kyletung.simplebookmovie.model.movie;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.data.movie.MovieTopData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.movie.IMovieTopView;
import com.kyletung.simplebookmovie.util.HttpUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 17:31<br>
 * <br>
 * 电影 Top 榜单的 Model
 */
public class MovieTopModel extends BaseModel {

    private static final int REQUEST_COUNT = 20;

    private boolean mHasMore = true;

    private IMovieTopView mView;

    public MovieTopModel(Context context, IMovieTopView view) {
        super(context);
        mView = view;
    }

    public void setDataInterface(IMovieTopView view) {
        this.mView = view;
    }

    /**
     * 获取数据
     */
    public void getData() {

        mHasMore = true;

        Map<String, String> params = new ArrayMap<>();
        params.put("start", String.valueOf(0));
        params.put("count", String.valueOf(REQUEST_COUNT));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl("/movie/top250", params), new HttpUtil.OnResultListener() {

            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieTopData data = getJsonUtil().fromJson(result, MovieTopData.class);
                    mView.onDataSuccess(data.getSubjects());
                }
            }

            @Override
            public void onError(String error) {
                System.out.println("result " + error);
                if (mView != null) {
                    mView.onDataError(error);
                }
            }

        });

    }

    /**
     * 获取更多
     *
     * @param start 开始计数点
     */
    public void getMore(int start) {

        if (!mHasMore) {
            mView.onMoreSuccess(new ArrayList<MovieSubject>());
        }

        Map<String, String> params = new ArrayMap<>();
        params.put("start", String.valueOf(start));
        params.put("count", String.valueOf(REQUEST_COUNT));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl("/movie/top250", params), new HttpUtil.OnResultListener() {

            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieTopData data = getJsonUtil().fromJson(result, MovieTopData.class);
                    mView.onMoreSuccess(data.getSubjects());
                    if (data.getSubjects().size() == 0) {
                        mHasMore = false;
                    }
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
