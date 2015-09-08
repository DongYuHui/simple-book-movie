package com.kyletung.doubanbookmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kyletung.doubanbookmovie.book.BookFragment;
import com.kyletung.doubanbookmovie.home.HomeFragment;
import com.kyletung.doubanbookmovie.movie.MovieFragment;
import com.kyletung.doubanbookmovie.search.SearchFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //init drawer layout and navigation view
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //init and fragment manager
    FragmentManager fragmentManager = getSupportFragmentManager();

    //set api key and secret key
    private static String API_KEY = "0208376c6d519a130618a64547f4ce39";
    private static String SECRET = "7e9cc2276a2ae982";
    private String authorizationCode;

    //get navigation header view
    CircleImageView navigationHeaderImage;
    TextView navigationHeaderIn;
    TextView navigationHeaderOut;
    TextView navigationHeaderName;

    //init toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init home
        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();

        //init toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);

        //set status bar
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.statusbar_background);

        //set tool bar toggle
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        //set navigation view click events
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                onItemClickedNavigationMenu(menuItem);
                return true;
            }
        });

        //init navigation header view
        navigationHeaderImage = (CircleImageView) findViewById(R.id.navigation_header_image);
        navigationHeaderIn = (TextView) findViewById(R.id.navigation_header_in);
        navigationHeaderOut = (TextView) findViewById(R.id.navigation_header_out);
        navigationHeaderName = (TextView) findViewById(R.id.navigation_header_name);
        navigationHeaderIn.setOnClickListener(this);
        navigationHeaderOut.setOnClickListener(this);
        initNavigationHeaderView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_header_in:
                Intent getAuthorizationCode = new Intent(MainActivity.this,GetAuthorizationCodeActivity.class);
                startActivityForResult(getAuthorizationCode, 827);
                break;
            case R.id.navigation_header_out:
                SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
                editor.remove("accessToken");
                navigationHeaderImage.setImageResource(R.mipmap.ic_launcher);
                navigationHeaderOut.setVisibility(View.GONE);
                navigationHeaderName.setVisibility(View.GONE);
                navigationHeaderIn.setVisibility(View.VISIBLE);
                break;
            case R.id.navigation_header_image:
                Toast.makeText(MainActivity.this, "User Detail Info", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_header_name:
                Toast.makeText(MainActivity.this, "User Detail Info", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void initNavigationHeaderView() {
        navigationHeaderName.setVisibility(View.GONE);
        navigationHeaderOut.setVisibility(View.GONE);
        if (!MyApplication.getAccessToken().equals("null")) {
            String url = "https://api.douban.com/v2/user/" + MyApplication.getUserId();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
//                        String name = response.getString("name");
                        navigationHeaderName.setText(response.getString("name"));
                        String avatar = response.getString("large_avatar");
                        ImageRequest avatarResquest = new ImageRequest(avatar, new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                navigationHeaderImage.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("slfwofihsoafjasd");
                            }
                        });
                        MyApplication.getRequestQueue().add(avatarResquest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("salfdaofheofasdfsf");
                }
            });
            MyApplication.getRequestQueue().add(jsonObjectRequest);
            navigationHeaderIn.setVisibility(View.GONE);
            navigationHeaderName.setVisibility(View.VISIBLE);
            navigationHeaderOut.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (requestCode == 827 && resultCode == 512) {
            authorizationCode = data.getStringExtra("authorizationCode");
            String url = "https://www.douban.com/service/auth2/token?client_id=" + API_KEY + "&client_secret=" + SECRET + "&redirect_uri=http://www.douban.com&grant_type=authorization_code&code=" + authorizationCode;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String accessToken = response.getString("access_token");
                        String userId = response.getString("douban_user_id");
                        String refreshToken = response.getString("refresh_token");
                        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
                        editor.putString("accessToken", accessToken);
                        editor.putString("userId", userId);
                        editor.putString("refreshToken", refreshToken);
                        editor.apply();
                        initNavigationHeaderView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(">>> token error <<<");
                }
            });
            MyApplication.getRequestQueue().add(jsonObjectRequest);
        }
    }

    private void onItemClickedNavigationMenu(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
            case R.id.navigation_menu_home:
                toolbar.setTitle("首页");
                fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.navigation_menu_book:
                toolbar.setTitle("书籍");
                fragmentManager.beginTransaction().replace(R.id.content, new BookFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.navigation_menu_movie:
                toolbar.setTitle("电影");
                fragmentManager.beginTransaction().replace(R.id.content, new MovieFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.navigation_menu_search:
                toolbar.setTitle("搜索");
                fragmentManager.beginTransaction().replace(R.id.content, new SearchFragment()).commit();
                drawerLayout.closeDrawer(navigationView);
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
