package com.kyletung.doubanbookmovie.book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;
import com.kyletung.doubanbookmovie.movie.MovieItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragmentReaded extends Fragment {

    private static String bookUrl = "https://api.douban.com/v2/book/user/" + MyApplication.getSharedPreferences().getString("userId", "null") + "/collections?start=";

    static int total = 10;
    static int count = 0;

    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager manager;
    private BookRecyclerAdapter adapter;

    public BookFragmentReaded() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_readed, container, false);

        //init recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_book_readed_recycler);
        adapter = new BookRecyclerAdapter();
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //init swipe refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_book_readed_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_red, R.color.google_green, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 0;
                adapter.clear();
                get();
            }
        });

        //set scroll to last
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        if (!swipeRefreshLayout.isRefreshing() && adapter.getItemCount() < total) {
                            swipeRefreshLayout.setRefreshing(true);
                            get();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    isSlidingToLast = false;
                } else {
                    isSlidingToLast = true;
                }
            }
        });

        //init when begin fragment
        get();
        swipeRefreshLayout.setRefreshing(true);

        return view;
    }

    public void get() {
        if (adapter.getItemCount() < total) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, bookUrl + count, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    adapter.notifyDataSetChanged();
                    try {
                        total = response.getInt("total");
                        count = count + response.getInt("count");
                        JSONArray array = response.getJSONArray("collections");
                        int countInOnce = 0;
                        for (int j = 0; j < array.length(); j++) {
                            if (array.getJSONObject(j).getString("status").equals("read") || array.getJSONObject(j).getString("status").equals("done")) {
                                add(array.getJSONObject(j));
                                countInOnce++;
                            }
                        }
                        if (countInOnce == 3) {
                            get();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MyApplication.getContext(), "刷新出错", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            MyApplication.getRequestQueue().add(jsonObjectRequest);
        }
    }

    public void add(JSONObject jsonObject) {
        try {
            BookItemData data = new BookItemData();
            data.setBookId(jsonObject.getString("book_id"));
            JSONObject book = jsonObject.getJSONObject("book");
            data.setBookTitle(book.getString("title"));
            data.setBookImage(book.getJSONObject("images").getString("medium"));
            data.setBookPoints(book.getJSONObject("rating").getString("average"));
            data.setBookDate(book.getString("pubdate"));
            data.setBookPrice(book.getString("price"));
            data.setBookPublisher(book.getString("publisher"));
            //set authors
            JSONArray authors = book.getJSONArray("author");
            StringBuilder authorString = new StringBuilder();
            for (int i = 0; i < authors.length(); i++) {
                if (i < authors.length() - 1) {
                    authorString.append(authors.getString(i));
                    authorString.append("、");
                } else {
                    authorString.append(authors.getString(i));
                }
            }
            data.setBookAuthor(authorString.toString());
            //set translators
            JSONArray translators = book.getJSONArray("translator");
            if (translators.length() == 0) {
                data.setBookTranslator("");
            } else {
                StringBuilder translatorString = new StringBuilder();
                for (int i = 0; i < translators.length(); i++) {
                    if (i < translators.length() - 1) {
                        translatorString.append(translators.getString(i));
                        translatorString.append("、");
                    } else {
                        translatorString.append(translators.getString(i));
                    }
                }
                data.setBookTranslator(translatorString.toString());
            }
            adapter.add(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
