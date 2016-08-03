package com.kyletung.simplebookmovie.view.recycler;

import android.support.v7.widget.GridLayoutManager;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/2/26.
 * 针对 GridLayoutManager 的自定义类，如果有 Footer，则 Footer 单独占据一行
 */
public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int spanSize;
    private MoreRecyclerAdapter adapter;

    public GridSpanSizeLookup(int spanSize, MoreRecyclerAdapter adapter) {
        this.spanSize = spanSize;
        this.adapter = adapter;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isHasFoot() && position == adapter.getItemCount() - 1) {
            return spanSize;
        } else {
            return 1;
        }
    }

}
