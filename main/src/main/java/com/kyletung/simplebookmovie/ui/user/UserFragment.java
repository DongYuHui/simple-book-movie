package com.kyletung.simplebookmovie.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.user.UserData;
import com.kyletung.simplebookmovie.event.UserEvent;
import com.kyletung.simplebookmovie.model.user.UserModel;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.HeadBackUtil;
import com.kyletung.simplebookmovie.util.ImageLoader;
import com.kyletung.simplebookmovie.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private LinearLayout mSettingContainer;

    private String mUserId;
    private UserModel mModel;

    public static UserFragment newInstance(String userId) {
        Bundle args = new Bundle();
        args.putString("userId", userId);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void init(View view) {
        // init data;;
        Bundle bundle = getArguments();
        mUserId = bundle.getString("userId");
        // init views
        mUserHeadBack = (ImageView) view.findViewById(R.id.user_head_back);
        mUserHead = (CircleImageView) view.findViewById(R.id.user_head);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserLocation = (TextView) view.findViewById(R.id.user_location);
        mUserSignature = (TextView) view.findViewById(R.id.user_signature);
        mUserDescription = (TextView) view.findViewById(R.id.user_description);
        mSettingContainer = (LinearLayout) view.findViewById(R.id.setting_container);
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
        // set listener
        setListener();
    }

    private void setListener() {
        mSettingContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseToast.toast(getActivity(), "Setting");
            }
        });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserIdEvent(UserEvent event) {
        mUserId = event.getUserId();
        mModel.getUserInfo(mUserId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
