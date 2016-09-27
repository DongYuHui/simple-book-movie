package com.kyletung.simplebookmovie.adapter.moviedetail;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.movie.Staff;
import com.kyletung.simplebookmovie.utils.ImageLoader;
import com.kyletung.simplebookmovie.view.recycler.BaseRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 13:31<br>
 * <br>
 * FixMe
 */
public class StaffAdapter extends BaseRecyclerAdapter<Staff, StaffAdapter.StaffViewHolder> {

    public StaffAdapter(Context context, int resource, Activity activity) {
        super(context, resource, activity);
    }

    @Override
    public StaffViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new StaffViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(StaffViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mActivity, holder.mStaffImage, mListData.get(itemPosition).getAvatars().getLarge());
        holder.mStaffName.setText(mListData.get(itemPosition).getName());
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {
        private ImageView mStaffImage;
        private TextView mStaffName;

        public StaffViewHolder(View itemView) {
            super(itemView);
            mStaffImage = (ImageView) itemView.findViewById(R.id.staff_image);
            mStaffName = (TextView) itemView.findViewById(R.id.staff_name);
        }
    }

}
