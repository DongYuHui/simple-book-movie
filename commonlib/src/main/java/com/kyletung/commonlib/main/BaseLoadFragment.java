package com.kyletung.commonlib.main;

import com.kyletung.commonlib.http.HttpErrorHandler;
import com.kyletung.commonlib.load.SwipeToLoadLayout;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: 2016/11/8 at 17:00<br>
 * 带有刷新与加载更多功能的 Fragment
 */
public abstract class BaseLoadFragment extends BaseFragment {

    protected SwipeToLoadLayout mLoadLayout;

    /**
     * 启用刷新
     *
     * @param canRefresh  是否可以刷新
     * @param canLoadMore 是否可以加载更多
     */
    protected void setRefresh(boolean canRefresh, boolean canLoadMore) {
        if (canRefresh) {
            mLoadLayout.setRefreshEnabled(true);
            mLoadLayout.setOnRefreshListener(this::onActionRefresh);
        } else {
            mLoadLayout.setRefreshEnabled(false);
            mLoadLayout.setOnRefreshListener(null);
        }
        if (canLoadMore) {
            mLoadLayout.setLoadMoreEnabled(true);
            mLoadLayout.setOnLoadMoreListener(this::onActionMore);
        } else {
            mLoadLayout.setLoadMoreEnabled(false);
            mLoadLayout.setOnLoadMoreListener(null);
        }
    }

    /**
     * 设置是否启用刷新
     *
     * @param enable 是否启用
     */
    protected void setRefreshEnable(boolean enable) {
        mLoadLayout.setRefreshEnabled(enable);
        if (enable) {
            mLoadLayout.setOnRefreshListener(this::onActionRefresh);
        }
    }

    /**
     * 设置是否启用加载更多
     *
     * @param enable 是否启用
     */
    protected void setMoreEnable(boolean enable) {
        mLoadLayout.setLoadMoreEnabled(enable);
        if (enable) {
            mLoadLayout.setOnLoadMoreListener(this::onActionMore);
        }
    }

    /**
     * 加载完成
     */
    protected void loadComplete() {
        if (mLoadLayout == null) return;
        if (mLoadLayout.isRefreshing()) mLoadLayout.setRefreshing(false);
        if (mLoadLayout.isLoadingMore()) mLoadLayout.setLoadingMore(false);
    }

    /**
     * 自动刷新
     */
    protected void autoRefresh() {
        mLoadLayout.post(() -> {
            mLoadLayout.setRefreshing(true);
            onActionRefresh();
        });
    }

    /**
     * 刷新动作
     */
    protected abstract void onActionRefresh();

    /**
     * 加载更多动作
     */
    protected abstract void onActionMore();

    /**
     * 重写该方法，当遇到错误时关闭刷新与加载更多
     *
     * @param action1 Action
     * @param <T>     泛型
     * @return 返回 Subscriber
     */
    @Override
    protected <T> Subscriber<T> newSubscriber(final Action1<T> action1) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                loadComplete();
                HttpErrorHandler.handle(getActivity(), e);
            }

            @Override
            public void onNext(T t) {
                action1.call(t);
            }

        };
    }

}
