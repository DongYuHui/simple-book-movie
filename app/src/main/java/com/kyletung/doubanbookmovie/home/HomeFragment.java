package com.kyletung.doubanbookmovie.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    static String consUrl = "http://web.juhe.cn:8080/constellation/getAll?key=22bb089e9c7440e75c0c699300d3dbb7&type=today&consName=";

    //init view
    TextView fragmentHomeDate;
    TextView fragmentHomeConslletion;
    TextView fragmentHomeQConslletion;
    TextView fragmentHomeColor;
    TextView fragmentHomeNumber;
    TextView fragmentHomeMoney;
    TextView fragmentHomeLove;
    TextView fragmentHomeLuck;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //init view
        fragmentHomeDate = (TextView) view.findViewById(R.id.fragment_home_date);
        fragmentHomeConslletion = (TextView) view.findViewById(R.id.fragment_home_conslletion);
        fragmentHomeQConslletion = (TextView) view.findViewById(R.id.fragment_home_qcons);
        fragmentHomeColor = (TextView) view.findViewById(R.id.fragment_home_today_color);
        fragmentHomeNumber = (TextView) view.findViewById(R.id.fragment_home_today_number);
        fragmentHomeMoney = (TextView) view.findViewById(R.id.fragment_home_today_money);
        fragmentHomeLove = (TextView) view.findViewById(R.id.fragment_home_today_love);
        fragmentHomeLuck = (TextView) view.findViewById(R.id.fragment_home_today_luck_content);

        //init swipe refresh layout
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_home_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_red, R.color.google_green, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //set view
        getData();

        return view;
    }

    public void getData() {
        int num = new Random().nextInt(12) + 1;
        String cons = "处女座";
        switch (num) {
            case 1:
                cons = "白羊座";
                break;
            case 2:
                cons = "金牛座";
                break;
            case 3:
                cons = "双子座";
                break;
            case 4:
                cons = "巨蟹座";
                break;
            case 5:
                cons = "狮子座";
                break;
            case 6:
                cons = "处女座";
                break;
            case 7:
                cons = "天秤座";
                break;
            case 8:
                cons = "天蝎座";
                break;
            case 9:
                cons = "射手座";
                break;
            case 10:
                cons = "摩羯座";
                break;
            case 11:
                cons = "水瓶座";
                break;
            case 12:
                cons = "双鱼座";
                break;
            default:
                break;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, consUrl + cons, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "刷新出错", Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getRequestQueue().add(jsonObjectRequest);
    }

    public void setData(JSONObject jsonObject) {
        try {
            fragmentHomeDate.setText(jsonObject.getString("datetime"));
            fragmentHomeConslletion.setText(jsonObject.getString("name"));
            fragmentHomeQConslletion.setText(jsonObject.getString("QFriend"));
            fragmentHomeColor.setText(jsonObject.getString("color"));
            fragmentHomeNumber.setText(jsonObject.getString("number"));
            fragmentHomeMoney.setText(jsonObject.getString("money"));
            fragmentHomeLove.setText(jsonObject.getString("love"));
            fragmentHomeLuck.setText(jsonObject.getString("summary"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
