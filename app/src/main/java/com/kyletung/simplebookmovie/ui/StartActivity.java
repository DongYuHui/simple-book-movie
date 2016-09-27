package com.kyletung.simplebookmovie.ui;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.WindowManager;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.ui.login.LoginActivity;
import com.kyletung.simplebookmovie.ui.main.MainActivity;
import com.kyletung.simplebookmovie.utils.UserInfoUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/11 at 16:59<br>
 * <br>
 * 起始页面
 */
public class StartActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void init() {
        // 判断系统版本，当大于等于 KitKat 时，设置状态栏与导航栏为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
        }
        // init data
        UserInfoUtil userInfoUtil = new UserInfoUtil(this);
        String userId = userInfoUtil.readUserId();
        if (TextUtils.isEmpty(userId)) {
            LoginActivity.start(this);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
        finish();
    }

}
