package com.kyletung.doubanbookmovie.moviedetail;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {

    String url = "https://api.douban.com/v2/movie/subject/";
    String mobileUrl;

    MovieDetailAdapter directorsAdapter;
    MovieDetailAdapter castsAdapter;

    //init window
    Window window;

    //init toolbar
    Toolbar toolbar;

    //init view
    ImageView movieImage;
    TextView movieTitle;
    TextView movieRealname;
    TextView movieYear;
    TextView movieCountry;
    TextView movieSummary;
    TextView moviePoints;

    RelativeLayout movieImageBackground;
    LinearLayout movieRealnameParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //set toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("电影详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //get movie id
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieId");

        //init view
        movieImage = (ImageView) findViewById(R.id.movie_detail_image);
        movieTitle = (TextView) findViewById(R.id.movie_detail_title);
        movieRealname = (TextView) findViewById(R.id.movie_detail_realname);
        movieYear = (TextView) findViewById(R.id.movie_detail_year);
        movieCountry = (TextView) findViewById(R.id.movie_detail_country);
        movieSummary = (TextView) findViewById(R.id.movie_detail_summary);
        moviePoints = (TextView) findViewById(R.id.movie_detail_points);

        movieImageBackground = (RelativeLayout) findViewById(R.id.movie_detail_image_background);
        movieRealnameParent = (LinearLayout) findViewById(R.id.movie_detail_realname_parent);

        //init window
        window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(getResources().getColor(R.color.movie_detail_default_background));
        }

        //init directors recycler view
        RecyclerView directorsRecycler = (RecyclerView) findViewById(R.id.movie_detail_directors);
        directorsAdapter = new MovieDetailAdapter(this);
        LinearLayoutManager directorsManager = new LinearLayoutManager(this);
        directorsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        directorsRecycler.setLayoutManager(directorsManager);
        directorsRecycler.setItemAnimator(new DefaultItemAnimator());
        directorsRecycler.setAdapter(directorsAdapter);

        //init casta recycler view
        RecyclerView castsRecycler = (RecyclerView) findViewById(R.id.movie_detail_casts);
        castsAdapter = new MovieDetailAdapter(this);
        LinearLayoutManager castsManager = new LinearLayoutManager(this);
        castsManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        castsRecycler.setLayoutManager(castsManager);
        castsRecycler.setItemAnimator(new DefaultItemAnimator());
        castsRecycler.setAdapter(castsAdapter);

        //get data
        getData(movieId);

    }

    public void getData(String movieId) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + movieId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieDetailActivity.this, "加载出错", Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getRequestQueue().add(jsonObjectRequest);
    }

    public void setData(JSONObject jsonObject) {
        try {
            mobileUrl = jsonObject.getString("mobile_url");
            movieTitle.setText(jsonObject.getString("title"));
            movieYear.setText(jsonObject.getString("year"));
            movieSummary.setText(jsonObject.getString("summary"));
            moviePoints.setText(jsonObject.getJSONObject("rating").getString("average"));
            //set realname
            if (jsonObject.getString("original_title").equals("")) {
                movieRealnameParent.setVisibility(View.GONE);
            } else {
                movieRealname.setText(jsonObject.getString("original_title"));
            }
            //set country
            JSONArray country = jsonObject.getJSONArray("countries");
            StringBuilder movieCountries = new StringBuilder();
            for (int i = 0; i < country.length(); i++) {
                if (i < country.length() - 1) {
                    movieCountries.append(country.getString(i));
                    movieCountries.append("、");
                } else {
                    movieCountries.append(country.getString(i));
                }
            }
            movieCountry.setText(movieCountries.toString());
            //set image
            ImageRequest imageRequest = new ImageRequest(jsonObject.getJSONObject("images").getString("large"), new Response.Listener<Bitmap>() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onResponse(Bitmap response) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        movieImage.setImageBitmap(response);
                        //set linearlayout background
                        try {
                            Palette palette = Palette.from(response).generate();
                            movieImageBackground.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                            window.setStatusBarColor(palette.getDarkVibrantSwatch().getRgb());
                            toolbar.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                        } catch (Exception e) {
                            movieImageBackground.setBackgroundColor(getResources().getColor(R.color.movie_detail_default_background));
                            window.setStatusBarColor(getResources().getColor(R.color.movie_detail_default_background));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.movie_detail_default_background));
                        }
                    } else {
                        movieImage.setImageBitmap(response);
                        //set linearlayout background
                        try {
                            Palette palette = Palette.from(response).generate();
                            movieImageBackground.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                            toolbar.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                        } catch (Exception e) {
                            movieImageBackground.setBackgroundColor(getResources().getColor(R.color.movie_detail_default_background));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.movie_detail_default_background));
                        }
                    }
                }
            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(">>> 图片加载错误 <<<");
                }
            });
            MyApplication.getRequestQueue().add(imageRequest);
            //set directors
            JSONArray directors = jsonObject.getJSONArray("directors");
            for (int i = 0; i < directors.length(); i++) {
                JSONObject director = directors.getJSONObject(i);
                MovieDetailItem item = new MovieDetailItem();
                item.setName(director.getString("name"));
                item.setUrl(director.getJSONObject("avatars").getString("medium"));
                directorsAdapter.add(item);
            }
            //set casts
            JSONArray casts = jsonObject.getJSONArray("casts");
            for (int i = 0; i < casts.length(); i++) {
                JSONObject actor = casts.getJSONObject(i);
                MovieDetailItem item = new MovieDetailItem();
                item.setName(actor.getString("name"));
                item.setUrl(actor.getJSONObject("avatars").getString("medium"));
                castsAdapter.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.movie_open_in_browser) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mobileUrl));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
