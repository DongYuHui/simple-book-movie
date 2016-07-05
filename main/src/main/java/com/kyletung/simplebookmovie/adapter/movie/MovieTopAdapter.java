package com.kyletung.simplebookmovie.adapter.movie;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.data.movie.Staff;
import com.kyletung.simplebookmovie.util.ImageLoader;
import com.kyletung.simplebookmovie.view.recycler.MoreRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/05 at 18:35<br>
 * <br>
 * FixMe
 */
public class MovieTopAdapter extends MoreRecyclerAdapter<MovieSubject, MovieTopAdapter.MovieViewHolder> {

    public MovieTopAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MovieTopAdapter(Context context, int resource, Activity activity) {
        super(context, resource, activity);
    }

    public MovieTopAdapter(Context context, int resource, Fragment fragment) {
        super(context, resource, fragment);
    }

    @Override
    public MovieViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(MovieViewHolder holder, int position) {
        final int itemPosition = position;
        if (mActivity != null) {
            ImageLoader.load(mActivity, holder.mMovieImage, mListData.get(itemPosition).getImages().getSmall());
        } else if (mFragment != null) {
            ImageLoader.load(mFragment, holder.mMovieImage, mListData.get(itemPosition).getImages().getSmall());
        } else {
            ImageLoader.load(mContext, holder.mMovieImage, mListData.get(itemPosition).getImages().getSmall());
        }
        holder.mMovieTitle.setText(mListData.get(itemPosition).getTitle());
        StringBuilder directors = new StringBuilder();
        for (Staff director : mListData.get(itemPosition).getDirectors()) {
            directors.append(director.getName()).append("、");
        }
        holder.mMovieDirector.setText(directors.substring(0, directors.length() - 1));
        StringBuilder casts = new StringBuilder();
        for (Staff cast : mListData.get(itemPosition).getCasts()) {
            casts.append(cast.getName()).append("、");
        }
        holder.mMovieCast.setText(casts.substring(0, casts.length() - 1));
        holder.mMovieYear.setText(mListData.get(itemPosition).getYear());
        holder.mMovieCollections.setText(String.valueOf(mListData.get(itemPosition).getCollect_count()));
        holder.mMovieContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Detail " + itemPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private CardView mMovieContainer;
        private ImageView mMovieImage;
        private TextView mMovieTitle;
        private TextView mMovieDirector;
        private TextView mMovieCast;
        private TextView mMovieYear;
        private TextView mMovieCollections;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieContainer = (CardView) itemView.findViewById(R.id.movie_container);
            mMovieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            mMovieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieDirector = (TextView) itemView.findViewById(R.id.movie_director);
            mMovieCast = (TextView) itemView.findViewById(R.id.movie_cast);
            mMovieYear = (TextView) itemView.findViewById(R.id.movie_year);
            mMovieCollections = (TextView) itemView.findViewById(R.id.movie_collections);
        }

    }
    
}
