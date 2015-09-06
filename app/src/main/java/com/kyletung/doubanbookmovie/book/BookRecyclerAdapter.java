package com.kyletung.doubanbookmovie.book;

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

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerViewHolder> {

    List<BookItemData> list;
    LayoutInflater inflater;

    public BookRecyclerAdapter() {
        list = new ArrayList<>();
        inflater = LayoutInflater.from(MyApplication.getContext());
    }

    public void add(BookItemData bookItemData) {
        list.add(bookItemData);
        notifyItemInserted(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public BookRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookRecyclerViewHolder(inflater.inflate(R.layout.fragment_book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BookRecyclerViewHolder holder, int position) {
        //set image
        ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BookImageCache());
        holder.bookImage.setDefaultImageResId(R.drawable.recycler_image_background);
        holder.bookImage.setErrorImageResId(R.mipmap.ic_launcher);
        holder.bookImage.setImageUrl(list.get(position).getBookImage(), imageLoader);

        //set others
        holder.bookName.setText(list.get(position).getBookTitle());
        holder.bookPoints.setText(list.get(position).getBookPoints());
        holder.bookAuthor.setText(list.get(position).getBookAuthor());
        holder.bookPublisher.setText(list.get(position).getBookPublisher());
        holder.bookPubdate.setText(list.get(position).getBookDate());
        holder.bookPrice.setText(list.get(position).getBookPrice());

        //set translator
        if (list.get(position).getBookTranslator().equals("")) {
            holder.bookDivider.setVisibility(View.GONE);
            holder.bookTranslator.setText("");
        } else {
            holder.bookTranslator.setText(list.get(position).getBookTranslator() + " 译");
        }
    }

    class BookImageCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> cache;

        public BookImageCache() {
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

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class BookRecyclerViewHolder extends RecyclerView.ViewHolder {

    NetworkImageView bookImage;
    TextView bookName;
    TextView bookPoints;
    TextView bookAuthor;
    TextView bookTranslator;
    TextView bookPublisher;
    TextView bookPubdate;
    TextView bookPrice;
    TextView bookDivider;

    public BookRecyclerViewHolder(View itemView) {
        super(itemView);
        bookImage = (NetworkImageView) itemView.findViewById(R.id.fragment_book_item_image);
        bookName = (TextView) itemView.findViewById(R.id.fragment_book_item_title);
        bookPoints = (TextView) itemView.findViewById(R.id.fragment_book_item_points);
        bookAuthor = (TextView) itemView.findViewById(R.id.fragment_book_item_author);
        bookTranslator = (TextView) itemView.findViewById(R.id.fragment_book_item_translator);
        bookPublisher = (TextView) itemView.findViewById(R.id.fragment_book_item_publisher);
        bookPubdate = (TextView) itemView.findViewById(R.id.fragment_book_item_pubdate);
        bookPrice = (TextView) itemView.findViewById(R.id.fragment_book_item_price);
        bookDivider = (TextView) itemView.findViewById(R.id.fragment_book_item_divider);
    }
}