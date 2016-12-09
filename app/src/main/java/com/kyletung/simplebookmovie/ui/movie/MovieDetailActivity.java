package com.kyletung.simplebookmovie.ui.movie;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.moviedetail.StaffAdapter;
import com.kyletung.simplebookmovie.client.request.MovieClient;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 22:19<br>
 * <br>
 * 影视详情页面
 */
public class MovieDetailActivity extends BaseActivity {

    // views
    @BindView(R.id.movie_detail_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_detail_title)
    TextView mMovieTitle;
    @BindView(R.id.movie_detail_points)
    TextView mMoviePoints;
    @BindView(R.id.movie_detail_real_name)
    TextView mMovieOriginalName;
    @BindView(R.id.movie_detail_year)
    TextView mMovieYear;
    @BindView(R.id.movie_detail_country)
    TextView mMovieCountry;
    @BindView(R.id.movie_detail_summary)
    TextView mMovieSummary;

    private StaffAdapter mCastAdapter; // 卡司适配器
    private StaffAdapter mDirectorAdapter; // 导演适配器

    @Override
    protected int getContentLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        // init toolbar
        setToolbar(getString(R.string.movie_detail_title), true);
        // set directors
        RecyclerView movieDirectors = (RecyclerView) findViewById(R.id.movie_detail_directors);
        movieDirectors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieDirectors.setItemAnimator(new DefaultItemAnimator());
        mDirectorAdapter = new StaffAdapter(this, R.layout.recycler_staff_item, this);
        movieDirectors.setAdapter(mDirectorAdapter);
        // set casts
        RecyclerView movieCasts = (RecyclerView) findViewById(R.id.movie_detail_casts);
        movieCasts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieCasts.setItemAnimator(new DefaultItemAnimator());
        mCastAdapter = new StaffAdapter(this, R.layout.recycler_staff_item, this);
        movieCasts.setAdapter(mCastAdapter);
    }

    @Override
    protected void business() {
        // init data
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieId");
        // init model
        getData(movieId);
    }

    /**
     * 获取详情数据
     *
     * @param movieId 影视 Id
     */
    private void getData(String movieId) {
        MovieClient.getInstance().getMovieDetail(movieId).subscribe(newSubscriber(this::getDataSuccess));
    }

    /**
     * 获取内容成功
     *
     * @param data 内容
     */
    public void getDataSuccess(MovieDetailData data) {
        ImageLoader.load(this, mMovieImage, data.getImages().getLarge());
        mMovieTitle.setText(data.getTitle());
        mMoviePoints.setText(String.valueOf(data.getRating().getAverage()));
        mMovieOriginalName.setText(data.getOriginal_title());
        mMovieYear.setText(data.getYear());
        if (data.getCountries() != null && data.getCountries().size() > 0) {
            StringBuilder countries = new StringBuilder();
            for (String country : data.getCountries()) {
                countries.append(country).append("/");
            }
            mMovieCountry.setText(countries.substring(0, countries.length() - 1));
        }
        mMovieSummary.setText(data.getSummary());
        mDirectorAdapter.putList(data.getDirectors());
        mCastAdapter.putList(data.getCasts());
    }

}
