package com.kyletung.doubanbookmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GetAuthorizationCodeActivity extends AppCompatActivity {

    private static String API_KEY = "0208376c6d519a130618a64547f4ce39";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_authorization_code);
        //init webview
        WebView webView = (WebView) findViewById(R.id.get_authorization_code_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.contains("www.kyletung.com/?code=")) {
                    String authorizationCode = url.substring(url.indexOf("=") + 1);
                    Intent intent = new Intent();
                    intent.putExtra("authorizationCode", authorizationCode);
                    setResult(512, intent);
                    finish();
                }
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl("https://www.douban.com/service/auth2/auth?client_id=" + API_KEY + "&redirect_uri=http://www.kyletung.com&response_type=code&scope=book_basic_r,book_basic_w,douban_basic_common,movie_basic_r");
    }

}
