package com.kyletung.simplebookmovie.ui.bookdetail;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.model.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.model.bookdetail.BookDetailModel;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.util.BaseToast;
import com.kyletung.simplebookmovie.util.ImageLoader;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 18:52<br>
 * <br>
 * FixMe
 */
public class BookDetailActivity extends BaseActivity implements IBookDetailView {

    // views
    private ImageView mBookImage;
    private TextView mBookTitle;
    private TextView mBookSubtitle;
    private TextView mBookPoints;
    private TextView mBookOriginalName;
    private TextView mBookAuthor;
    private TextView mBookPublisher;
    private TextView mBookTranslator;
    private TextView mBookPubDate;
    private TextView mBookPrice;
    private TextView mBookAuthorSummary;
    private TextView mBookSummary;
    private TextView mBookCatalog;

    // model
    private BookDetailModel mModel;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void init() {
        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.book_detail_title);
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
        String bookId = intent.getStringExtra("bookId");
        String userId = intent.getStringExtra("userId");
        // init views
        mBookImage = (ImageView) findViewById(R.id.book_detail_image);
        mBookTitle = (TextView) findViewById(R.id.book_detail_title);
        mBookSubtitle = (TextView) findViewById(R.id.book_detail_subtitle);
        mBookPoints = (TextView) findViewById(R.id.book_detail_points);
        mBookOriginalName = (TextView) findViewById(R.id.book_detail_real_name);
        mBookAuthor = (TextView) findViewById(R.id.book_detail_author);
        mBookPublisher = (TextView) findViewById(R.id.book_detail_publisher);
        mBookTranslator = (TextView) findViewById(R.id.book_detail_translator);
        mBookPubDate = (TextView) findViewById(R.id.book_detail_pub_date);
        mBookPrice = (TextView) findViewById(R.id.book_detail_price);
        mBookAuthorSummary = (TextView) findViewById(R.id.book_detail_author_summary);
        mBookSummary = (TextView) findViewById(R.id.book_detail_summary);
        mBookCatalog = (TextView) findViewById(R.id.book_detail_catalog);
        // init model
        mModel = new BookDetailModel(this);
        mModel.setInterface(this);
        if (TextUtils.isEmpty(userId)) {
            mModel.getData(bookId);
        } else {
            mModel.getCollection(bookId, userId);
        }
    }

    private void setData(BookDetailData data) {
        ImageLoader.load(this, mBookImage, data.getImages().getLarge());
        mBookTitle.setText(data.getTitle());
        mBookSubtitle.setText(data.getSubtitle());
        mBookPoints.setText(data.getRating().getAverage());
        mBookOriginalName.setText(data.getOrigin_title());
        StringBuilder builder = new StringBuilder();
        for (String author : data.getAuthor()) {
            builder.append(author).append("、");
        }
        mBookAuthor.setText(builder.substring(0, builder.length() - 1));
        mBookPublisher.setText(data.getPublisher());
        if (data.getTranslator() != null && data.getTranslator().size() > 0) {
            StringBuilder translators = new StringBuilder();
            for (String translator : data.getTranslator()) {
                translators.append(translator).append("、");
            }
            mBookTranslator.setText(translators.substring(0, translators.length() - 1));
        }
        mBookPubDate.setText(data.getPubdate());
        mBookPrice.setText(data.getPrice());
        mBookAuthorSummary.setText(data.getAuthor_intro());
        mBookSummary.setText(data.getSummary());
        mBookCatalog.setText(data.getCatalog());
    }

    @Override
    public void onGetDataSuccess(BookDetailData data) {
        setData(data);
    }

    @Override
    public void onGetDataError(String error) {
        BaseToast.toast(this, error);
    }

    @Override
    public void onGetCollectionSuccess(BookCollectionData data) {
        setData(data.getBook());
    }

    @Override
    public void onGetCollectionError(String error) {
        BaseToast.toast(this, error);
    }

}
