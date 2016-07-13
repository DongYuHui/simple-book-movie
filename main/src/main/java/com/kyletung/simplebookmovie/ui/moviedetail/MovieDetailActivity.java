package com.kyletung.simplebookmovie.ui.moviedetail;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
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

    @Override
    protected int getContentLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void init() {
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
    }

    @Override
    public void onGetDataError(String error) {
        BaseToast.toast(this, error);
    }

}
