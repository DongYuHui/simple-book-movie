package com.kyletung.simplebookmovie.ui.settings;

import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.event.LogoutEvent;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.util.UserInfoUtil;
import com.kyletung.simplebookmovie.util.VersionUtil;

import org.greenrobot.eventbus.EventBus;

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
    private LinearLayout mSettingsAbout;

    private TextView mSettingsVersion;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init() {
        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings_title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.tool_bar_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // init views
        mSettingsLogout = (LinearLayout) findViewById(R.id.settings_logout);
        mSettingsAbout = (LinearLayout) findViewById(R.id.settings_about);
        mSettingsVersion = (TextView) findViewById(R.id.settings_version_number);
        // set listener
        setListener();
    }

    private void setListener() {
        mSettingsLogout.setOnClickListener(this);
        mSettingsAbout.setOnClickListener(this);
        mSettingsVersion.post(new Runnable() {
            @Override
            public void run() {
                try {
                    mSettingsVersion.setText(VersionUtil.getInstance().getVersion(SettingsActivity.this));
                } catch (PackageManager.NameNotFoundException e) {
                    mSettingsVersion.setText(String.valueOf(0));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_logout:
                new UserInfoUtil(this).removeInfo();
                EventBus.getDefault().post(new LogoutEvent());
                finish();
                break;
            case R.id.settings_about:
                AboutFragment aboutFragment = AboutFragment.newInstance();
                aboutFragment.show(getSupportFragmentManager(), "About");
                break;
        }
    }

}
