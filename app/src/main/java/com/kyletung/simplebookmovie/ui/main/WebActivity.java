package com.kyletung.simplebookmovie.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.simplebookmovie.R;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/3/30<br>
 * <br>
 * FixMe
 */
public class WebActivity extends BaseActivity {

    private static final String ENTRY_TITLE = "entry_title";
    private static final String ENTRY_URL = "entry_url";

    @BindView(R.id.web_progress)
    ProgressBar mProgress;
    @BindView(R.id.web_view)
    WebView mWebView;

    private String mTitle;
    private String mUrl;

    public static void start(Context context, String title, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(ENTRY_TITLE, title);
        starter.putExtra(ENTRY_URL, url);
        context.startActivity(starter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_web;
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void initView() {
        // init data
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(ENTRY_TITLE);
        mUrl = intent.getStringExtra(ENTRY_URL);
        // set toolbar
        setToolbar(mTitle, true);
        // init WebView
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgress.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgress.setVisibility(View.GONE);
                } else {
                    mProgress.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void business() {
        mWebView.post(() -> mWebView.loadUrl(mUrl));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).removeView(mWebView);
        mWebView.stopLoading();
        mWebView.destroy();
        mWebView = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
