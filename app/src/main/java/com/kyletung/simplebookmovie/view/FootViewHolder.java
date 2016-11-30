package com.kyletung.simplebookmovie.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.kyletung.simplebookmovie.R;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/1/12.
 * The Footer of The Custom RecyclerView Adapter
 */
public class FootViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar mProgressBar;

    public FootViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.recycler_view_footer_progress);
    }

}
