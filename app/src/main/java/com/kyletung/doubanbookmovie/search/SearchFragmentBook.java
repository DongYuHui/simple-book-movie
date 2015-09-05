package com.kyletung.doubanbookmovie.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.doubanbookmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragmentBook extends Fragment {


    public SearchFragmentBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_book, container, false);
    }


}
