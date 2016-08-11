package com.kyletung.simplebookmovie.ui.settings;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.ui.login.LoginActivity;
import com.kyletung.simplebookmovie.util.UserInfoUtil;
import com.kyletung.simplebookmovie.util.VersionUtil;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 15:06<br>
 * <br>
 * FixMe
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mSettingsLogout;

    private TextView mSettingsVersion;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init() {
        // init toolbar
        setToolbar(getString(R.string.settings_title), true);
        // init views
        mSettingsLogout = (LinearLayout) findViewById(R.id.settings_logout);
        mSettingsVersion = (TextView) findViewById(R.id.settings_version_number);
        // set listener
        setListener();
    }

    private void setListener() {
        mSettingsLogout.setOnClickListener(this);
        try {
            mSettingsVersion.setText(VersionUtil.getInstance().getVersion(SettingsActivity.this));
        } catch (PackageManager.NameNotFoundException e) {
            mSettingsVersion.setText(String.valueOf(0));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_logout:
                new UserInfoUtil(this).removeInfo();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
