package com.kyletung.simplebookmovie.model.bookdetail;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.bookdetail.IBookDetailView;
import com.kyletung.simplebookmovie.util.HttpUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 21:01<br>
 * <br>
 * FixMe
 */
public class BookDetailModel extends BaseModel {

    private IBookDetailView mView;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BookDetailModel(Context context) {
        super(context);
    }

    public void setInterface(IBookDetailView view) {
        mView = view;
    }

    public void getData(String bookId) {
        getHttpUtil().get(getContext(), getUrlUtil().bindUrl(String.format("/book/%s", bookId)), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    BookDetailData data = getJsonUtil().fromJson(result, BookDetailData.class);
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
    }

    public void getCollection(String bookId, String userId) {
        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("user_id", userId);
        getHttpUtil().get(getContext(), getUrlUtil().buildUrl(String.format("/book/%s/collection", bookId), params), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    BookCollectionData data = getJsonUtil().fromJson(result, BookCollectionData.class);
                    mView.onGetCollectionSuccess(data);
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onGetCollectionError(error);
                }
            }
        });
    }

}
