package com.kyletung.doubanbookmovie.bookdetail;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class BookDetailActivity extends AppCompatActivity {

    //url
    static String url = "https://api.douban.com/v2/book/";

    //book id
    String bookId;
    String mobileUrl;

    //init view
    RelativeLayout bookImageBackground;
    ImageView bookImage;
    TextView bookTitle;
    TextView bookSubtitle;
    TextView bookRealname;
    TextView bookPublisher;
    TextView bookTranslator;
    TextView bookPubdate;
    TextView bookPrice;
    TextView bookAuthor;
    TextView bookAuthorSummary;
    TextView bookSummary;
    CardView bookMine;
    TextView bookMyStatus;
    TextView bookMySummary;
    ImageView bookMyStar1;
    ImageView bookMyStar2;
    ImageView bookMyStar3;
    ImageView bookMyStar4;
    ImageView bookMyStar5;
    TextView bookCatalog;
    LinearLayout bookTranLayout;
    LinearLayout bookRealLayout;
    TextView bookPoints;

    //init window
    Window window;

    //init toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //set toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("书籍详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //init view
        bookImageBackground = (RelativeLayout) findViewById(R.id.book_detail_image_background);
        bookImage = (ImageView) findViewById(R.id.book_detail_image);
        bookTitle = (TextView) findViewById(R.id.book_detail_title);
        bookSubtitle = (TextView) findViewById(R.id.book_detail_subtitle);
        bookRealname = (TextView) findViewById(R.id.book_detail_realname);
        bookPublisher = (TextView) findViewById(R.id.book_detail_publisher);
        bookTranslator = (TextView) findViewById(R.id.book_detail_translator);
        bookPubdate = (TextView) findViewById(R.id.book_detail_pubdate);
        bookPrice = (TextView) findViewById(R.id.book_detail_price);
        bookAuthor = (TextView) findViewById(R.id.book_detail_author);
        bookAuthorSummary = (TextView) findViewById(R.id.book_detail_author_summary);
        bookSummary = (TextView) findViewById(R.id.book_detail_summary);
        bookMine = (CardView) findViewById(R.id.book_detail_mine);
        bookMyStatus = (TextView) findViewById(R.id.book_detail_status);
        bookMySummary = (TextView) findViewById(R.id.book_detail_mysummary);
        bookMyStar1 = (ImageView) findViewById(R.id.book_detail_star_1);
        bookMyStar2 = (ImageView) findViewById(R.id.book_detail_star_2);
        bookMyStar3 = (ImageView) findViewById(R.id.book_detail_star_3);
        bookMyStar4 = (ImageView) findViewById(R.id.book_detail_star_4);
        bookMyStar5 = (ImageView) findViewById(R.id.book_detail_star_5);
        bookCatalog = (TextView) findViewById(R.id.book_detail_catalog);
        bookTranLayout = (LinearLayout) findViewById(R.id.book_detail_tran_layout);
        bookRealLayout = (LinearLayout) findViewById(R.id.book_detail_real_layout);
        bookPoints = (TextView) findViewById(R.id.book_detail_points);

        //init window
        window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(getResources().getColor(R.color.book_detail_default_background));
        }

        //get book id
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");

        //set data
        getData(bookId);
    }

    public void getData(String bookId) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + bookId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null && error.networkResponse.statusCode == 400) {
                    String errorString = new String(error.networkResponse.data);
                    handleError(errorString);
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + MyApplication.getSharedPreferences().getString("accessToken", "null"));
                return map;
            }
        };
        MyApplication.getRequestQueue().add(jsonObjectRequest);
    }

    public void setData(JSONObject jsonObject) {
        try {
            mobileUrl = jsonObject.getString("alt");
            bookPubdate.setText(jsonObject.getString("pubdate"));
            bookSubtitle.setText(jsonObject.getString("subtitle"));
            bookPublisher.setText(jsonObject.getString("publisher"));
            bookPoints.setText(jsonObject.getJSONObject("rating").getString("average"));
            bookTitle.setText(jsonObject.getString("title"));
            bookPrice.setText(jsonObject.getString("price"));
            bookAuthorSummary.setText(jsonObject.getString("author_intro"));
            bookSummary.setText(jsonObject.getString("summary"));
            bookCatalog.setText(jsonObject.getString("catalog"));
            //set origin title
            if (!jsonObject.getString("origin_title").equals("")) {
                bookRealname.setText(jsonObject.getString("origin_title"));
            } else {
                bookRealLayout.setVisibility(View.GONE);
            }
            //set author
            JSONArray authors = jsonObject.getJSONArray("author");
            for (int i = 0; i < authors.length(); i++) {
                StringBuilder author = new StringBuilder();
                if (i != authors.length() - 1) {
                    author.append(authors.getString(i));
                    author.append("、");
                } else {
                    author.append(authors.getString(i));
                }
                bookAuthor.setText(author.toString());
            }
            //set translator
            JSONArray translators = jsonObject.getJSONArray("translator");
            if (translators.length() != 0) {
                for (int i = 0; i < translators.length(); i++) {
                    StringBuilder translator = new StringBuilder();
                    if (i != translators.length() - 1) {
                        translator.append(authors.getString(i));
                        translator.append("、");
                    } else {
                        translator.append(authors.getString(i));
                    }
                    bookTranslator.setText(translator.toString());
                }
            } else {
                bookTranLayout.setVisibility(View.GONE);
            }
            //set image
            ImageRequest imageRequest = new ImageRequest(jsonObject.getJSONObject("images").getString("large"), new Response.Listener<Bitmap>() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onResponse(Bitmap response) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        bookImage.setImageBitmap(response);
                        //set image layout background
                        try {
                            Palette palette = Palette.from(response).generate();
                            bookImageBackground.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                            window.setStatusBarColor(palette.getDarkVibrantSwatch().getRgb());
                            toolbar.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                        } catch (Exception e) {
                            bookImageBackground.setBackgroundColor(getResources().getColor(R.color.book_detail_default_background));
                            window.setStatusBarColor(getResources().getColor(R.color.book_detail_default_background));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.book_detail_default_background));
                        }
                    } else {
                        bookImage.setImageBitmap(response);
                        //set image layout background
                        try {
                            Palette palette = Palette.from(response).generate();
                            bookImageBackground.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                            toolbar.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                        } catch (Exception e) {
                            bookImageBackground.setBackgroundColor(getResources().getColor(R.color.book_detail_default_background));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.book_detail_default_background));
                        }
                    }
                }
            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(BookDetailActivity.this, "图片加载错误", Toast.LENGTH_SHORT).show();
                }
            });
            MyApplication.getRequestQueue().add(imageRequest);
            //set my card
            try {
                JSONObject mine = jsonObject.getJSONObject("current_user_collection");
                //set status
                String status = mine.getString("status");
                if (!status.equals("wish")) {
                    if (status.equals("read") || status.equals("done")) {
                        bookMyStatus.setText("已读");
                    } else {
                        bookMyStatus.setText("在读");
                    }
                    //set summary
                    bookMySummary.setText(mine.getString("comment"));
                    //set star
                    String value = mine.getJSONObject("rating").getString("value");
                    switch (value) {
                        case "1":
                            bookMyStar1.setImageResource(R.drawable.ic_star_grey600_24dp);
                            break;
                        case "2":
                            bookMyStar1.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar2.setImageResource(R.drawable.ic_star_grey600_24dp);
                            break;
                        case "3":
                            bookMyStar1.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar2.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar3.setImageResource(R.drawable.ic_star_grey600_24dp);
                            break;
                        case "4":
                            bookMyStar1.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar2.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar3.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar4.setImageResource(R.drawable.ic_star_grey600_24dp);
                            break;
                        case "5":
                            bookMyStar1.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar2.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar3.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar4.setImageResource(R.drawable.ic_star_grey600_24dp);
                            bookMyStar5.setImageResource(R.drawable.ic_star_grey600_24dp);
                            break;
                    }
                } else {
                    bookMyStatus.setText("想读");
                    ImageView dividerOne = (ImageView) findViewById(R.id.book_detail_mine_divider_one);
                    ImageView dividerTwo = (ImageView) findViewById(R.id.book_detail_mine_divider_two);
                    LinearLayout mineSummaryLayout = (LinearLayout) findViewById(R.id.book_detail_mine_summary_layout);
                    LinearLayout mineStarLayout = (LinearLayout) findViewById(R.id.book_detail_mine_star_layout);
                    dividerOne.setVisibility(View.GONE);
                    dividerTwo.setVisibility(View.GONE);
                    mineSummaryLayout.setVisibility(View.GONE);
                    mineStarLayout.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                bookMine.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void handleError(String error) {
        try {
            JSONObject jsonObject = new JSONObject(error);
            int errorCode = jsonObject.getInt("code");
            switch (errorCode) {
                case 106:
                    refreshToken();
                    Toast.makeText(BookDetailActivity.this, "正在重新授权，请稍后", Toast.LENGTH_SHORT).show();
                    break;
                case 111:
                    Toast.makeText(BookDetailActivity.this, "访问过于频繁，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                case 112:
                    Toast.makeText(BookDetailActivity.this, "访问过于频繁，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                case 121:
                    Toast.makeText(BookDetailActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            Toast.makeText(BookDetailActivity.this, "未知网络错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void refreshToken() {
        String API_KEY = "0208376c6d519a130618a64547f4ce39";
        String SECRET = "7e9cc2276a2ae982";
        String url = "https://www.douban.com/service/auth2/token?client_id=" + API_KEY + "&client_secret=" + SECRET + "&redirect_uri=http://www.douban.com&grant_type=refresh_token&refresh_token=";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url + MyApplication.getRefreshToken(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
                try {
                    editor.putString("accessToken", response.getString("access_token"));
                    editor.putString("refreshToken", response.getString("refresh_token"));
                    editor.apply();
                    getData(bookId);
                } catch (JSONException e) {
                    Toast.makeText(BookDetailActivity.this, "重新授权出错，请重新登录", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookDetailActivity.this, "重新授权出错，请重新登录", Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.book_open_in_browser) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mobileUrl));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
