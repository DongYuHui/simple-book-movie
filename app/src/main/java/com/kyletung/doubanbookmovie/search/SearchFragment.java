package com.kyletung.doubanbookmovie.search;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    //init viewpager and tablayout
    ViewPager viewPager;
    TabLayout tabLayout;

    //init search pager adapter
    SearchPagerAdapter adapter;

    //init edit text
    EditText input;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //init view pager and tab layout
        viewPager = (ViewPager) view.findViewById(R.id.fragment_search_viewpager);
        adapter = new SearchPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_search_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //fix tablayout no title bug
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        //init edit text and search button
        input = (EditText) view.findViewById(R.id.fragment_search_text);
        ImageButton search = (ImageButton) view.findViewById(R.id.fragment_search_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        return view;
    }

    public void getData() {
        if (!input.getText().toString().equals("")) {
            String inputText = input.getText().toString();
            inputText = inputText.replace(" ", "\b");
            SearchFragmentMovie movieSearchFragment = (SearchFragmentMovie) adapter.getItem(1);
            movieSearchFragment.search(inputText);
            SearchFragmentBook bookSearchFragment = (SearchFragmentBook) adapter.getItem(0);
            bookSearchFragment.search(inputText);
        } else {
            Toast.makeText(getActivity(), "输入为空", Toast.LENGTH_SHORT).show();
        }
    }

}
