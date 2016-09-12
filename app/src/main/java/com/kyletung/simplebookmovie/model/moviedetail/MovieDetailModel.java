package com.kyletung.simplebookmovie.model.moviedetail;

import android.content.Context;

import com.kyletung.simplebookmovie.client.MovieClient;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.moviedetail.IMovieDetailView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 22:15<br>
 * <br>
 * FixMe
 */
public class MovieDetailModel extends BaseModel {

    private IMovieDetailView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public MovieDetailModel(Context context) {
        super(context);
    }

    public void setInterface(IMovieDetailView view) {
        this.mView = view;
    }

    public void getData(String movieId) {
        getHttpUtil().get(getContext(), getUrlUtil().bindUrl(String.format("/movie/subject/%s", movieId)), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    MovieDetailData data = getJsonUtil().fromJson(result, MovieDetailData.class);
                    mView.onGetDataSuccess(data);
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onGetDataError(error);
                }
            }
        });
        MovieClient.getInstance().getMovieDetail(movieId);
    }

}
