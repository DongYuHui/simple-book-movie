package com.kyletung.simplebookmovie.adapter.book;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.adapter.BaseRecyclerAdapter;
import com.kyletung.commonlib.adapter.BaseViewHolder;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.book.BookItem;
import com.kyletung.simplebookmovie.data.book.BookSubject;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:57<br>
 * <br>
 * 书籍列表适配器
 */
public class BookAdapter extends BaseRecyclerAdapter<BookItem, BookAdapter.BookViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public BookAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public BookViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(createView(parent));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindDataViewHolder(BookViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mContext, holder.mImage, mListData.get(itemPosition).getBook().getImages().getLarge());
        holder.mTitle.setText(mListData.get(itemPosition).getBook().getTitle());
        holder.mPoints.setText(mListData.get(itemPosition).getBook().getRating().getAverage());
        if (mListData.get(itemPosition).getBook().getAuthor() != null && mListData.get(itemPosition).getBook().getAuthor().size() > 0) {
            StringBuilder authors = new StringBuilder();
            for (String author : mListData.get(itemPosition).getBook().getAuthor()) {
                authors.append(author).append("、");
            }
            holder.mAuthor.setText(authors.substring(0, authors.length() - 1));
        }
        if (mListData.get(itemPosition).getBook().getTranslator().size() > 0) {
            StringBuilder translators = new StringBuilder();
            for (String translator : mListData.get(itemPosition).getBook().getTranslator()) {
                translators.append(translator).append("、");
            }
            holder.mTranslator.setText(translators.substring(0, translators.length() - 1));
        }
        holder.mPublisher.setText(mListData.get(itemPosition).getBook().getPublisher());
        holder.mPubDate.setText(mListData.get(itemPosition).getBook().getPubdate());
        holder.mPrice.setText(mListData.get(itemPosition).getBook().getPrice());
        holder.mContainer.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(holder.mImage, itemPosition, mListData.get(itemPosition).getBook());
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(View cover, int position, BookSubject bookSubject);
    }

}
