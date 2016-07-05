/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * modify from https://github.com/JorgeCastilloPrz/Mirage
 */

package com.kyletung.simplebookmovie.view.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Jorge Castillo Pérez
 *         modify at 2015/08/23
 */
public class LinearOnScrollListener extends RecyclerView.OnScrollListener {

    // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private boolean loading = false;

    private OnLoadMore onLoadMore;

    private LinearLayoutManager mLinearLayoutManager;
    private MoreRecyclerAdapter moreRecyclerAdapter;

    public LinearOnScrollListener(LinearLayoutManager linearLayoutManager, MoreRecyclerAdapter moreRecyclerAdapter) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.moreRecyclerAdapter = moreRecyclerAdapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        //totalItemCount > visibleItemCount 超过一个页面才有加载更多
        if (!loading &&                                                                 //当前没在滑动状态
                totalItemCount >= visibleItemCount &&                                   //超过一个页面
                (totalItemCount - visibleItemCount) <= firstVisibleItem &&              //滑动到最底部
                dy > 0) {                                                               //页面在向下滑动
            // End has been reached
            loading = true;
            moreRecyclerAdapter.setHasFoot(true);
            if (onLoadMore != null) onLoadMore.onLoadMore();
        }

    }

    /**
     * Load More Complete
     */
    public void loadComplete() {
        loading = false;
        moreRecyclerAdapter.setHasFoot(false);
    }

    /**
     * 设置回调接口
     *
     * @param onLoadMore 回调
     */
    public void setOnLoadMore(OnLoadMore onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    /**
     * 定义一个接口，当 RecyclerView 滑动到底部加载更多时回调
     */
    public interface OnLoadMore {
        void onLoadMore();
    }

}
