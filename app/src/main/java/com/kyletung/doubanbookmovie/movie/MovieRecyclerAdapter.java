package com.kyletung.doubanbookmovie.movie;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.kyletung.doubanbookmovie.MyApplication;
import com.kyletung.doubanbookmovie.R;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerViewHolder> {

    List<MovieItemData> list;
    LayoutInflater inflater;

    public MovieRecyclerAdapter() {
        list = new ArrayList<>();
        inflater = LayoutInflater.from(MyApplication.getContext());
    }

    public void add(MovieItemData movieItemData) {
        list.add(movieItemData);
        notifyItemInserted(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public MovieRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieRecyclerViewHolder(inflater.inflate(R.layout.fragment_movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieRecyclerViewHolder holder, int position) {
        //init image
        ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new MovieImageCache());
        holder.fragmentMovieImage.setDefaultImageResId(R.drawable.recycler_image_background);
        holder.fragmentMovieImage.setErrorImageResId(R.mipmap.ic_launcher);
        holder.fragmentMovieImage.setImageUrl(list.get(position).getMovieImage(), imageLoader);

        //init others
        holder.fragmentMovieTitle.setText(list.get(position).getMovieName());
        holder.fragmentMoviePoints.setText(list.get(position).getMoviePoints() + "");
        holder.fragmentMovieDirector.setText(list.get(position).getMovieDirector());
        holder.fragmentMovieCast.setText(list.get(position).getMovieCasts());
        holder.fragmentMovieYear.setText(list.get(position).getMovieYear());
        holder.fragmentMovieCollections.setText(list.get(position).getMovieCollections() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieImageCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> cache;

        public MovieImageCache() {
            int maxSize = 10 * 1024 * 1024;
            cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
    }

}

class MovieRecyclerViewHolder extends RecyclerView.ViewHolder {

    NetworkImageView fragmentMovieImage;
    TextView fragmentMovieTitle;
    TextView fragmentMoviePoints;
    TextView fragmentMovieDirector;
    TextView fragmentMovieCast;
    TextView fragmentMovieYear;
    TextView fragmentMovieCollections;

    public MovieRecyclerViewHolder(View itemView) {
        super(itemView);
        fragmentMovieImage = (NetworkImageView) itemView.findViewById(R.id.fragment_movie_item_image);
        fragmentMovieTitle = (TextView) itemView.findViewById(R.id.fragment_movie_item_title);
        fragmentMoviePoints = (TextView) itemView.findViewById(R.id.fragment_movie_item_points);
        fragmentMovieDirector = (TextView) itemView.findViewById(R.id.fragment_movie_item_director);
        fragmentMovieCast = (TextView) itemView.findViewById(R.id.fragment_movie_item_cast);
        fragmentMovieYear = (TextView) itemView.findViewById(R.id.fragment_movie_item_year);
        fragmentMovieCollections = (TextView) itemView.findViewById(R.id.fragment_movie_item_collections);
    }
}