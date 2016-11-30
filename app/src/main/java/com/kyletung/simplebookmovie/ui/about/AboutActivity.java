package com.kyletung.simplebookmovie.ui.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.utils.VersionUtil;

import butterknife.BindView;

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

    @BindView(R.id.version)
    TextView mVersion;

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        // set action bar
        setToolbar(getString(R.string.about_title), true);
        // set version
        try {
            mVersion.setText(String.format(
                    getString(R.string.about_version),
                    String.valueOf(VersionUtil.getInstance().getVersion(this))
            ));
        } catch (PackageManager.NameNotFoundException e) {
            mVersion.setText(getString(R.string.about_version_error));
        }
    }

    @Override
    protected void business() {
    }

}
