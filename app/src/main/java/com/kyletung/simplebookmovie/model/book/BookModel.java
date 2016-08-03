package com.kyletung.simplebookmovie.model.book;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.kyletung.simplebookmovie.data.book.BookData;
import com.kyletung.simplebookmovie.data.book.BookItem;
import com.kyletung.simplebookmovie.model.BaseModel;
import com.kyletung.simplebookmovie.ui.book.IBookView;
import com.kyletung.simplebookmovie.util.HttpUtil;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:50<br>
 * <br>
 * FixMe
 */
public class BookModel extends BaseModel {

    private String status;
    private String userId;

    private IBookView mView;

    private boolean mHasMore = true;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BookModel(Context context) {
        super(context);
    }

    public void setInterface(IBookView view, String status, String userId) {
        mView = view;
        this.status = status;
        this.userId = userId;
    }

    public void getData() {

        mHasMore = true;

        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("status", status);
        params.put("start", String.valueOf(0));
        params.put("count", String.valueOf(20));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl(String.format("/book/user/%s/collections", userId), params), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    BookData data = getJsonUtil().fromJson(result, BookData.class);
                    mView.onDataSuccess(data.getCollections());
                }
            }

            @Override
            public void onError(String error) {
                if (mView != null) {
                    mView.onDataError(error);
                }
            }
        });

    }

    public void getMore(int start) {

        if (!mHasMore) {
            mView.onMoreSuccess(new ArrayList<BookItem>());
        }

        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("status", status);
        params.put("start", String.valueOf(start));
        params.put("count", String.valueOf(20));

        getHttpUtil().get(getContext(), getUrlUtil().buildUrl(String.format("/book/user/%s/collections", userId), params), new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                if (mView != null) {
                    BookData data = getJsonUtil().fromJson(result, BookData.class);
                    mView.onMoreSuccess(data.getCollections());
                    if (data.getCollections().size() == 0) {
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
