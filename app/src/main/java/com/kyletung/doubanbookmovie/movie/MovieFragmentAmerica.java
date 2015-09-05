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
public class MovieFragmentAmerica extends Fragment {

    private static String americaUrl = "https://api.douban.com/v2/movie/us_box";

    private MovieRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager manager;

    public MovieFragmentAmerica() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_america, container, false);

        //init recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_movie_america_recycler);
        manager = new LinearLayoutManager(getActivity());
        adapter = new MovieRecyclerAdapter();
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //init swipe refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_movie_america_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_red, R.color.google_green, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                get();
            }
        });

        //init when begin fragment
        get();
        swipeRefreshLayout.setRefreshing(true);

        return view;
    }

    public void get() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, americaUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                adapter.notifyDataSetChanged();
                try {
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

    public void add(JSONObject jsonObject) {
        try {
            MovieItemData data = new MovieItemData();
            JSONObject item = jsonObject.getJSONObject("subject");
            data.setMovieId(item.getString("id"));
            data.setMovieName(item.getString("title"));
            data.setMovieImage(item.getJSONObject("images").getString("medium"));
            data.setMoviePoints(item.getJSONObject("rating").getDouble("average"));
            data.setMovieYear(item.getString("year"));
            data.setMovieCollections(item.getInt("collect_count"));
            //set director
            JSONArray directors = item.getJSONArray("directors");
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
            JSONArray casts = item.getJSONArray("casts");
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
