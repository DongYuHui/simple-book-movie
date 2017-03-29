package com.kyletung.simplebookmovie.adapter.search;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.adapter.BaseViewHolder;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.book.BookSubject;
import com.kyletung.simplebookmovie.data.movie.MovieSubject;
import com.kyletung.simplebookmovie.data.movie.Staff;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Create Time: DongYuHui at 2017/3/29<br>
 * <br>
 * FixMe
 */
public class SearchAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private Type mNowType = Type.BOOK;

    private List<BookSubject> mBookList;
    private List<MovieSubject> mMovieList;

    private OnBookClickListener mOnBookClickListener;
    private OnMovieClickListener mOnMovieClickListener;

    public SearchAdapter(Context context) {
        mContext = context;
        mBookList = new ArrayList<>();
        mMovieList = new ArrayList<>();
    }

    public void setModeBook() {
        mNowType = Type.BOOK;
    }

    public void setModeMovie() {
        mNowType = Type.MOVIE;
    }

    public void putBookList(List<BookSubject> list) {
        if (list == null) return;
        mBookList.clear();
        notifyDataSetChanged();
        mBookList.addAll(list);
        notifyItemRangeInserted(0, list.size());
    }

    public void addBookList(List<BookSubject> list) {
        int start = mBookList.size();
        mBookList.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }

    public void putMovieList(List<MovieSubject> list) {
        if (list == null) return;
        mMovieList.clear();
        notifyDataSetChanged();
        mMovieList.addAll(list);
        notifyItemRangeInserted(0, list.size());
    }

    public void addMovieList(List<MovieSubject> list) {
        int start = mMovieList.size();
        mMovieList.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new BookViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_book_item, parent, false));
        } else if (viewType == 1) {
            return new MovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_movie_item, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mNowType == Type.BOOK) {
            onBindBook((BookViewHolder) holder, position);
        } else {
            onBindMovie((MovieViewHolder) holder, position);
        }
    }

    private void onBindBook(BookViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mContext, holder.mImage, mBookList.get(itemPosition).getImages().getLarge());
        holder.mTitle.setText(mBookList.get(itemPosition).getTitle());
        holder.mPoints.setText(mBookList.get(itemPosition).getRating().getAverage());
        if (mBookList.get(itemPosition).getTranslator() != null && mBookList.get(itemPosition).getTranslator().size() > 0) {
            StringBuilder authors = new StringBuilder();
            for (String author : mBookList.get(itemPosition).getAuthor()) {
                authors.append(author).append("、");
            }
            if (!TextUtils.isEmpty(authors))
                holder.mAuthor.setText(authors.substring(0, authors.length() - 1));
        }
        if (mBookList.get(itemPosition).getTranslator() != null && mBookList.get(itemPosition).getTranslator().size() > 0) {
            StringBuilder translators = new StringBuilder();
            for (String translator : mBookList.get(itemPosition).getTranslator()) {
                translators.append(translator).append("、");
            }
            if (!TextUtils.isEmpty(translators))
                holder.mTranslator.setText(translators.substring(0, translators.length() - 1));
        }
        holder.mPublisher.setText(mBookList.get(itemPosition).getPublisher());
        holder.mPubDate.setText(mBookList.get(itemPosition).getPubdate());
        holder.mPrice.setText(mBookList.get(itemPosition).getPrice());
        holder.mContainer.setOnClickListener(view -> {
            if (mOnBookClickListener != null) {
                mOnBookClickListener.onClick(itemPosition, mBookList.get(itemPosition).getId());
            }
        });
    }

    private void onBindMovie(MovieViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mContext, holder.mMovieImage, mMovieList.get(itemPosition).getImages().getLarge());
        holder.mMovieTitle.setText(mMovieList.get(itemPosition).getTitle());
        if (mMovieList.get(itemPosition).getDirectors() != null && mMovieList.get(itemPosition).getDirectors().size() > 0) {
            StringBuilder directors = new StringBuilder();
            for (Staff director : mMovieList.get(itemPosition).getDirectors()) {
                directors.append(director.getName()).append("、");
            }
            if (!TextUtils.isEmpty(directors))
                holder.mMovieDirector.setText(directors.substring(0, directors.length() - 1));
        }
        if (mMovieList.get(itemPosition).getCasts() != null && mMovieList.get(itemPosition).getCasts().size() > 0) {
            StringBuilder casts = new StringBuilder();
            for (Staff cast : mMovieList.get(itemPosition).getCasts()) {
                casts.append(cast.getName()).append("、");
            }
            if (!TextUtils.isEmpty(casts))
                holder.mMovieCast.setText(casts.substring(0, casts.length() - 1));
        }
        holder.mMovieYear.setText(mMovieList.get(itemPosition).getYear());
        holder.mMovieCollections.setText(String.valueOf(mMovieList.get(itemPosition).getCollect_count()));
        holder.mMovieContainer.setOnClickListener(view -> {
            if (mOnMovieClickListener != null) {
                mOnMovieClickListener.onClick(itemPosition, String.valueOf(mMovieList.get(itemPosition).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mNowType == Type.BOOK) {
            return mBookList.size();
        } else {
            return mMovieList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mNowType == Type.BOOK) {
            return 0;
        } else {
            return 1;
        }
    }

    private enum Type {
        BOOK,
        MOVIE
    }

    class BookViewHolder extends BaseViewHolder {
        @BindView(R.id.book_container)
        CardView mContainer;
        @BindView(R.id.book_image)
        ImageView mImage;
        @BindView(R.id.book_title)
        TextView mTitle;
        @BindView(R.id.book_points)
        TextView mPoints;
        @BindView(R.id.book_author)
        TextView mAuthor;
        @BindView(R.id.book_translator)
        TextView mTranslator;
        @BindView(R.id.book_publisher)
        TextView mPublisher;
        @BindView(R.id.book_pub_date)
        TextView mPubDate;
        @BindView(R.id.book_price)
        TextView mPrice;

        BookViewHolder(View itemView) {
            super(itemView);
        }
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

    public void setOnBookClickListener(OnBookClickListener onBookClickListener) {
        mOnBookClickListener = onBookClickListener;
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        mOnMovieClickListener = onMovieClickListener;
    }

    public interface OnBookClickListener {
        void onClick(int position, String bookId);
    }

    public interface OnMovieClickListener {
        void onClick(int position, String movieId);
    }

}
