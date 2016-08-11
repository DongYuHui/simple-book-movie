package com.kyletung.simplebookmovie.ui.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.user.UserData;
import com.kyletung.simplebookmovie.model.user.UserModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.HeadBackUtil;
import com.kyletung.simplebookmovie.util.ImageLoader;
import com.kyletung.simplebookmovie.util.UserInfoUtil;
import com.kyletung.simplebookmovie.view.CircleImageView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 13:31<br>
 * <br>
 * 用户页面 Fragment
 */
public class UserFragment extends BaseFragment implements IUserView {

    // user info
    private ImageView mUserHeadBack;
    private CircleImageView mUserHead;
    private TextView mUserName;
    private TextView mUserLocation;
    private TextView mUserSignature;
    private TextView mUserDescription;

    private String mUserId;
    private UserModel mModel;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void init(View view) {
        // init data;;
        mUserId = new UserInfoUtil(getActivity()).readUserId();
        // init views
        mUserHeadBack = (ImageView) view.findViewById(R.id.user_head_back);
        mUserHead = (CircleImageView) view.findViewById(R.id.user_head);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserLocation = (TextView) view.findViewById(R.id.user_location);
        mUserSignature = (TextView) view.findViewById(R.id.user_signature);
        mUserDescription = (TextView) view.findViewById(R.id.user_description);
        // init head background
        ImageLoader.load(this, mUserHeadBack, new HeadBackUtil(getActivity()).getImage());
        // init model
        mModel = new UserModel(getActivity());
        mModel.setInterface(this);
        // get info
        if (!TextUtils.isEmpty(mUserId)) {
            mUserHead.post(new Runnable() {
                @Override
                public void run() {
                    mModel.getUserInfo(mUserId);
                }
            });
        }
    }

    @Override
    public void onGetInfoSuccess(UserData info) {
        ImageLoader.load(this, mUserHead, info.getLarge_avatar(), false);
        mUserName.setText(info.getName());
        mUserLocation.setText(info.getLoc_name());
        mUserSignature.setText(info.getSignature());
        mUserDescription.setText(info.getDesc());
    }

    @Override
    public void onGetInfoError(String error) {
        BaseToast.toast(getActivity(), error);
    }

}
