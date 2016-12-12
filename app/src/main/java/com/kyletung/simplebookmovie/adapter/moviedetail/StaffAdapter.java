package com.kyletung.simplebookmovie.adapter.moviedetail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.adapter.BaseViewHolder;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.movie.Staff;
import com.kyletung.simplebookmovie.view.BaseRecyclerAdapter;

import butterknife.BindView;

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

    public StaffAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public StaffViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new StaffViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(StaffViewHolder holder, int position) {
        ImageLoader.load(mContext, holder.mStaffImage, mListData.get(position).getAvatars().getLarge());
        holder.mStaffName.setText(mListData.get(position).getName());
    }

    class StaffViewHolder extends BaseViewHolder {

        @BindView(R.id.staff_image)
        ImageView mStaffImage;
        @BindView(R.id.staff_name)
        TextView mStaffName;

        StaffViewHolder(View itemView) {
            super(itemView);
        }
    }

}
