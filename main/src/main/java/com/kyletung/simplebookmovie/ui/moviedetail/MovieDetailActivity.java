package com.kyletung.simplebookmovie.ui.moviedetail;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.adapter.moviedetail.StaffAdapter;
import com.kyletung.simplebookmovie.data.moviedetail.MovieDetailData;
import com.kyletung.simplebookmovie.model.moviedetail.MovieDetailModel;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.ImageLoader;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 22:19<br>
 * <br>
 * FixMe
 */
public class MovieDetailActivity extends BaseActivity implements IMovieDetailView {

    // views
    private ImageView mMovieImage;
    private TextView mMovieTitle;
    private TextView mMoviePoints;
    private TextView mMovieOriginalName;
    private TextView mMovieYear;
    private TextView mMovieCountry;
    private TextView mMovieSummary;

    private MovieDetailModel mModel;
    private StaffAdapter mDirectorAdapter;
    private StaffAdapter mCastAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void init() {
        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movie_detail_title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.tool_bar_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // init data
        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movieId");
        // init views
        mMovieImage = (ImageView) findViewById(R.id.movie_detail_image);
        mMovieTitle = (TextView) findViewById(R.id.movie_detail_title);
        mMoviePoints = (TextView) findViewById(R.id.movie_detail_points);
        mMovieOriginalName = (TextView) findViewById(R.id.movie_detail_real_name);
        mMovieYear = (TextView) findViewById(R.id.movie_detail_year);
        mMovieCountry = (TextView) findViewById(R.id.movie_detail_country);
        mMovieSummary = (TextView) findViewById(R.id.movie_detail_summary);
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
        // init model
        mModel = new MovieDetailModel(this);
        mModel.setInterface(this);
        mModel.getData(movieId);
    }

    @Override
    public void onGetDataSuccess(MovieDetailData data) {
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

    @Override
    public void onGetDataError(String error) {
        BaseToast.toast(this, error);
    }

}
