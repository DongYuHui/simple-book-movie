package com.kyletung.doubanbookmovie.movie;


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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragmentTop250 extends Fragment {

    private static String top250Url = "https://api.douban.com/v2/movie/top250?start=";

    static int total = 10;

    private MovieRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager manager;

    public MovieFragmentTop250() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_top250, container, false);

        //init recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_movie_top250_recycler);
        manager = new LinearLayoutManager(getActivity());
        adapter = new MovieRecyclerAdapter(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //init swipe refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_movie_top250_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_red, R.color.google_green, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, top250Url + adapter.getItemCount(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    adapter.notifyDataSetChanged();
                    try {
                        total = response.getInt("total");
                        JSONArray array = response.getJSONArray("subjects");
                        for (int j = 0; j < array.length(); j++) {
                            add(array.getJSONObject(j));
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
            MovieItemData data = new MovieItemData();
            data.setMovieId(jsonObject.getString("id"));
            data.setMovieName(jsonObject.getString("title"));
            data.setMovieImage(jsonObject.getJSONObject("images").getString("medium"));
            data.setMoviePoints(jsonObject.getJSONObject("rating").getDouble("average"));
            data.setMovieYear(jsonObject.getString("year"));
            data.setMovieCollections(jsonObject.getInt("collect_count"));
            //set director
            JSONArray directors = jsonObject.getJSONArray("directors");
            StringBuilder directorsString = new StringBuilder();
            for (int i = 0; i < directors.length(); i++) {
                if (i < directors.length() - 1) {
                    directorsString.append(directors.getJSONObject(i).getString("name"));
                    directorsString.append("、");
                } else {
                    directorsString.append(directors.getJSONObject(i).getString("name"));
                }
            }
            data.setMovieDirector(directorsString.toString());
            //set casts
            JSONArray casts = jsonObject.getJSONArray("casts");
            StringBuilder castsString = new StringBuilder();
            for (int i = 0; i < casts.length(); i++) {
                if (i < casts.length() - 1) {
                    castsString.append(casts.getJSONObject(i).getString("name"));
                    castsString.append("、");
                } else {
                    castsString.append(casts.getJSONObject(i).getString("name"));
                }
            }
            data.setMovieCasts(castsString.toString());
            adapter.add(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
