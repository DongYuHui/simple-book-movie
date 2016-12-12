package com.kyletung.simplebookmovie.adapter.movie;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.adapter.BaseViewHolder;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.movie.MovieItem;
import com.kyletung.simplebookmovie.data.movie.Staff;
import com.kyletung.simplebookmovie.view.BaseRecyclerAdapter;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 18:12<br>
 * <br>
 * 电影列表的适配器
 */
public class MovieBoardAdapter extends BaseRecyclerAdapter<MovieItem, MovieBoardAdapter.MovieViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public MovieBoardAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(MovieViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mContext, holder.mMovieImage, mListData.get(itemPosition).getSubject().getImages().getLarge());
        holder.mMovieTitle.setText(mListData.get(itemPosition).getSubject().getTitle());
        if (mListData.get(itemPosition).getSubject().getDirectors() != null && mListData.get(itemPosition).getSubject().getDirectors().size() > 0) {
            StringBuilder directors = new StringBuilder();
            for (Staff director : mListData.get(itemPosition).getSubject().getDirectors()) {
                directors.append(director.getName()).append("、");
            }
            holder.mMovieDirector.setText(directors.substring(0, directors.length() - 1));
        }
        if (mListData.get(itemPosition).getSubject().getCasts() != null && mListData.get(itemPosition).getSubject().getCasts().size() > 0) {
            StringBuilder casts = new StringBuilder();
            for (Staff cast : mListData.get(itemPosition).getSubject().getCasts()) {
                casts.append(cast.getName()).append("、");
            }
            holder.mMovieCast.setText(casts.substring(0, casts.length() - 1));
        }
        holder.mMovieYear.setText(mListData.get(itemPosition).getSubject().getYear());
        holder.mMovieCollections.setText(String.valueOf(mListData.get(itemPosition).getSubject().getCollect_count()));
        holder.mMovieContainer.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(itemPosition, String.valueOf(mListData.get(itemPosition).getSubject().getId()));
            }
        });
    }

    class MovieViewHolder extends BaseViewHolder {

        @BindView(R.id.movie_container)
        CardView mMovieContainer;
        @BindView(R.id.movie_image)
        ImageView mMovieImage;
        @BindView(R.id.movie_title)
        TextView mMovieTitle;
        @BindView(R.id.movie_director)
        TextView mMovieDirector;
        @BindView(R.id.movie_cast)
        TextView mMovieCast;
        @BindView(R.id.movie_year)
        TextView mMovieYear;
        @BindView(R.id.movie_collections)
        TextView mMovieCollections;

        MovieViewHolder(View itemView) {
            super(itemView);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, String movieId);
    }

}
