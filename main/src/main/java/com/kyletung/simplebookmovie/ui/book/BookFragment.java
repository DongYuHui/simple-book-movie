package com.kyletung.simplebookmovie.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.book.BookPagerAdapter;
import com.kyletung.simplebookmovie.event.UserEvent;
import com.kyletung.simplebookmovie.ui.BaseFragment;
import com.kyletung.simplebookmovie.ui.login.LoginActivity;
import com.kyletung.simplebookmovie.view.TabViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 21:07<br>
 * <br>
 * 书籍页面
 */
public class BookFragment extends BaseFragment {

    private String mUserId;

    // login view
    private Button mLoginButton;
    private RelativeLayout mLoginContainer;

    // content view
    private TabLayout mTabLayout;
    private TabViewPager mViewPager;

    public static BookFragment newInstance(String userId) {
        Bundle args = new Bundle();
        args.putString("userId", userId);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_book;
    }

    @Override
    protected void init(View view) {
        // init data
        Bundle bundle = getArguments();
        mUserId = bundle.getString("userId");
        // init login views
        mLoginButton = (Button) view.findViewById(R.id.book_login_button);
        mLoginContainer = (RelativeLayout) view.findViewById(R.id.book_login_container);
        // init content views
        mTabLayout = (TabLayout) view.findViewById(R.id.book_tab);
        mViewPager = (TabViewPager) view.findViewById(R.id.book_viewpager);
        mViewPager.setSwipeEnabled(true);
        // judge user id
        if (TextUtils.isEmpty(mUserId)) {
            System.out.println("book userId: no userId");
            mLoginContainer.setVisibility(View.VISIBLE);
        } else {
            System.out.println("book userId: has userId");
            mLoginContainer.setVisibility(View.GONE);
            onUserIdEvent(new UserEvent(mUserId));
        }
        // set listener
        setListener();
    }

    private void setListener() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserIdEvent(UserEvent event) {
        mLoginContainer.setVisibility(View.GONE);
        BookPagerAdapter adapter = new BookPagerAdapter(getChildFragmentManager(), event.getUserId());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
