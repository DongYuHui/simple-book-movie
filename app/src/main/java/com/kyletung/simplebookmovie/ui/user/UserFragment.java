package com.kyletung.simplebookmovie.ui.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.client.AccountClient;
import com.kyletung.simplebookmovie.client.IResponse;
import com.kyletung.simplebookmovie.data.user.UserData;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.HeadBackUtil;
import com.kyletung.simplebookmovie.util.ImageLoader;
import com.kyletung.simplebookmovie.util.UserInfoUtil;
import com.kyletung.simplebookmovie.view.CircleImageView;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 13:31<br>
 * <br>
 * 用户页面 Fragment
 */
public class UserFragment extends BaseFragment {

    @BindView(R.id.user_head)
    CircleImageView mUserHead;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_location)
    TextView mUserLocation;
    @BindView(R.id.user_signature)
    TextView mUserSignature;
    @BindView(R.id.user_description)
    TextView mUserDescription;

    private String mUserId;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void init(View view) {
        // init data
        mUserId = new UserInfoUtil(getActivity()).readUserId();
        // init views
        ImageView userHeadBack = (ImageView) view.findViewById(R.id.user_head_back);
        // init head background
        ImageLoader.load(this, userHeadBack, new HeadBackUtil(getActivity()).getImage());
        // get info
        if (!TextUtils.isEmpty(mUserId)) {
            mUserHead.post(new Runnable() {
                @Override
                public void run() {
                    getData(mUserId);
                }
            });
        }
    }

    /**
     * 获取用户资料
     *
     * @param userId 用户 Id
     */
    private void getData(String userId) {
        AccountClient.getInstance().getUserData(userId, new IResponse<UserData>() {

            @Override
            public void onResponse(UserData result) {
                getInfoSuccess(result);
            }

            @Override
            public void onError(int code, String reason) {
                getInfoError(reason);
            }

        });
    }

    /**
     * 获取用户资料成功
     *
     * @param info 用户信息
     */
    public void getInfoSuccess(UserData info) {
        ImageLoader.load(this, mUserHead, info.getLarge_avatar(), false);
        mUserName.setText(info.getName());
        mUserLocation.setText(info.getLoc_name());
        mUserSignature.setText(info.getSignature());
        mUserDescription.setText(info.getDesc());
    }

    /**
     * 获取用户资料失败
     *
     * @param error 失败原因
     */
    public void getInfoError(String error) {
        BaseToast.toast(getActivity(), error);
    }

}
