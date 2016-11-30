package com.kyletung.simplebookmovie.adapter.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.commonlib.utils.ToastUtil;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.book.BookSubject;
import com.kyletung.simplebookmovie.view.MoreRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 21:47<br>
 * <br>
 * FixMe
 */
public class SearchBookAdapter extends MoreRecyclerAdapter<BookSubject, SearchBookAdapter.BookViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public SearchBookAdapter(Context context, int resource, Fragment fragment) {
        super(context, resource, fragment);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public BookViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(BookViewHolder holder, int position) {
        final int itemPosition = position;
        ImageLoader.load(mFragment, holder.mImage, mListData.get(itemPosition).getImages().getLarge());
        holder.mTitle.setText(mListData.get(itemPosition).getTitle());
        holder.mPoints.setText(mListData.get(itemPosition).getRating().getAverage());
        if (mListData.get(itemPosition).getTranslator() != null && mListData.get(itemPosition).getTranslator().size() > 0) {
            StringBuilder authors = new StringBuilder();
            for (String author : mListData.get(itemPosition).getAuthor()) {
                authors.append(author).append("、");
            }
            holder.mAuthor.setText(authors.substring(0, authors.length() - 1));
        }
        if (mListData.get(itemPosition).getTranslator() != null && mListData.get(itemPosition).getTranslator().size() > 0) {
            StringBuilder translators = new StringBuilder();
            for (String translator : mListData.get(itemPosition).getTranslator()) {
                translators.append(translator).append("、");
            }
            holder.mTranslator.setText(translators.substring(0, translators.length() - 1));
        }
        holder.mPublisher.setText(mListData.get(itemPosition).getPublisher());
        holder.mPubDate.setText(mListData.get(itemPosition).getPubdate());
        holder.mPrice.setText(mListData.get(itemPosition).getPrice());
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(mContext, "Position " + itemPosition);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(itemPosition, mListData.get(itemPosition).getId());
                }
            }
        });
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        private CardView mContainer;
        private ImageView mImage;
        private TextView mTitle;
        private TextView mPoints;
        private TextView mAuthor;
        private TextView mTranslator;
        private TextView mPublisher;
        private TextView mPubDate;
        private TextView mPrice;

        public BookViewHolder(View itemView) {
            super(itemView);
            mContainer = (CardView) itemView.findViewById(R.id.book_container);
            mImage = (ImageView) itemView.findViewById(R.id.book_image);
            mTitle = (TextView) itemView.findViewById(R.id.book_title);
            mPoints = (TextView) itemView.findViewById(R.id.book_points);
            mAuthor = (TextView) itemView.findViewById(R.id.book_author);
            mTranslator = (TextView) itemView.findViewById(R.id.book_translator);
            mPublisher = (TextView) itemView.findViewById(R.id.book_publisher);
            mPubDate = (TextView) itemView.findViewById(R.id.book_pub_date);
            mPrice = (TextView) itemView.findViewById(R.id.book_price);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, String bookId);
    }
    
}
