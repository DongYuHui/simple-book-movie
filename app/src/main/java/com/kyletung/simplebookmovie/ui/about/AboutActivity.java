package com.kyletung.simplebookmovie.ui.about;

import android.content.Context;
import android.content.Intent;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.ui.settings.AboutFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/11 at 21:06<br>
 * <br>
 * 关于页面
 */
public class AboutActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {
        // set action bar
        setToolbar(getString(R.string.settings_about), true);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, AboutFragment.newInstance()).commit();
    }

}
