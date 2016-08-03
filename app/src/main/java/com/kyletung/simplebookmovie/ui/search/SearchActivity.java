package com.kyletung.simplebookmovie.ui.search;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.search.SearchPagerAdapter;
import com.kyletung.simplebookmovie.event.SearchEvent;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.view.TabViewPager;

import org.greenrobot.eventbus.EventBus;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 19:52<br>
 * <br>
 * FixMe
 */
public class SearchActivity extends BaseActivity {

    private EditText mSearch;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        // init data
        Intent intent = getIntent();
        String searchContent = intent.getStringExtra("content");
        // init search
        mSearch = (EditText) findViewById(R.id.search_content);
        mSearch.setText(searchContent);
        // init view
        TabLayout tabLayout = (TabLayout) findViewById(R.id.search_tab);
        TabViewPager viewPager = (TabViewPager) findViewById(R.id.search_viewpager);
        SearchPagerAdapter adapter = new SearchPagerAdapter(getSupportFragmentManager(), searchContent);
        viewPager.setSwipeEnabled(true);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // set listener
        setListener();
    }

    private void setListener() {
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    EventBus.getDefault().post(new SearchEvent(textView.getText().toString()));
                }
                return false;
            }
        });
    }

}
