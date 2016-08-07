package com.kyletung.simplebookmovie.model;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kyletung.simplebookmovie.util.UserInfoUtil;
import com.kyletung.simplebookmovie.util.VolleyErrorHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/08/06 at 20:33<br>
 * <br>
 * FixMe
 */
public class TestModel extends BaseModel {

    String url = "https://www.douban.com/service/auth2/auth?client_id=0208376c6d519a130618a64547f4ce39&redirect_uri=http://www.kyletung.com&response_type=code&scope=book_basic_r,book_basic_w,douban_basic_common,movie_basic_r";

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public TestModel(Context context) {
        super(context);
        test();
        getToken();
    }

    private void test() {
        Map<String, String> params = new HashMap<>();
        params.put("user_alias", "dyh920827@gmail.com");
        params.put("user_passwd", "foreachHer*100");
        params.put("confirm", "授权");
        params.put("redirect_url", "http://www.kyletung.com");
        params.put("client_id", "0208376c6d519a130618a64547f4ce39");
        params.put("response_type", "code");
//        getHttpUtil().post(getContext(), url, new HttpUtil.OnResultListener() {
//            @Override
//            public void onSuccess(String result) {
//                System.out.println("test success : " + result);
//            }
//
//            @Override
//            public void onError(String error) {
//                System.out.println("test error : " + error);
//            }
//        }, params);
    }

    private void getToken() {
        final String token = new UserInfoUtil(getContext()).readAccessToken();
        String refresh = new UserInfoUtil(getContext()).readRefreshToken();
        String userId = new UserInfoUtil(getContext()).readUserId();
        System.out.println("test user info : " + token + " " + refresh + " " + userId);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, "https://api.douban.com/v2/user/61394142", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("test user info success : " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("test user info error");
                VolleyErrorHandler.handleError(error, new VolleyErrorHandler.OnOauthListener() {

                    @Override
                    public void onRefreshSuccess(String result) {

                    }

                    @Override
                    public void onOauthError(String error) {
                        System.out.println("test user info error : " + error);
                    }

                });
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + token);
                return map;
            }
        };
        stringRequest.setTag(this);
        getHttpUtil().getRequestQueue().add(stringRequest);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.douban.com/v2/book/20470849", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("test book detail success : " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("test book detail error : " + error.networkResponse.statusCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + token);
                return map;
            }
        };
        getHttpUtil().getRequestQueue().add(jsonObjectRequest);

    }

}
