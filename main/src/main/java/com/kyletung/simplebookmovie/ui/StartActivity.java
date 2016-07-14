package com.kyletung.simplebookmovie.ui;

import android.content.Intent;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
import com.kyletung.simplebookmovie.util.UserInfoUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 16:59<br>
 * <br>
 * FixMe
 */
public class StartActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void init() {
        // init data
        UserInfoUtil userInfoUtil = new UserInfoUtil(this);
        String userId = userInfoUtil.readUserId();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

}
