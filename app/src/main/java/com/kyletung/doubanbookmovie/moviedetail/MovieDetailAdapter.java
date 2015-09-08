package com.kyletung.doubanbookmovie.moviedetail;

import android.content.Context;
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

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailViewHolder> {

    List<MovieDetailItem> list;
    LayoutInflater inflater;
    Context context;

    public MovieDetailAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void add(MovieDetailItem movieDetailItem) {
        list.add(movieDetailItem);
        notifyItemInserted(list.size() - 1);
    }

    @Override
    public MovieDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieDetailViewHolder(inflater.inflate(R.layout.activity_movie_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieDetailViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        //set image view
        ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new MovieImageCache());
        holder.image.setDefaultImageResId(R.drawable.recycler_image_background);
        holder.image.setErrorImageResId(R.mipmap.ic_launcher);
        holder.image.setImageUrl(list.get(position).getUrl(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieImageCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> cache;

        public MovieImageCache() {
            int maxSize = 2 * 1024 * 1024;
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

class MovieDetailViewHolder extends RecyclerView.ViewHolder {

    NetworkImageView image;
    TextView name;

    public MovieDetailViewHolder(View itemView) {
        super(itemView);
        image = (NetworkImageView) itemView.findViewById(R.id.activity_movie_detail_item_image);
        name = (TextView) itemView.findViewById(R.id.activity_movie_detail_item_name);
    }
}