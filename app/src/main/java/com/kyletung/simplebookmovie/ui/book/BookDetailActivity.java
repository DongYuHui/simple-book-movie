package com.kyletung.simplebookmovie.ui.book;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.client.IResponse;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.ui.BaseActivity;
import com.kyletung.simplebookmovie.utils.BaseToast;
import com.kyletung.simplebookmovie.utils.ImageLoader;

import butterknife.BindView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 18:52<br>
 * <br>
 * 书籍详情页面
 */
public class BookDetailActivity extends BaseActivity {

    // views
    @BindView(R.id.book_detail_image)
    ImageView mBookImage; // 封面
    @BindView(R.id.book_detail_title)
    TextView mBookTitle; // 书籍标题
    @BindView(R.id.book_detail_subtitle)
    TextView mBookSubtitle; // 书籍副标题
    @BindView(R.id.book_detail_points)
    TextView mBookPoints; // 评分
    @BindView(R.id.book_detail_real_name)
    TextView mBookOriginalName; // 书籍原名
    @BindView(R.id.book_detail_author)
    TextView mBookAuthor; // 作者
    @BindView(R.id.book_detail_publisher)
    TextView mBookPublisher; // 出版社
    @BindView(R.id.book_detail_translator)
    TextView mBookTranslator; // 翻译者
    @BindView(R.id.book_detail_pub_date)
    TextView mBookPubDate; // 出版日期
    @BindView(R.id.book_detail_price)
    TextView mBookPrice; // 价格
    @BindView(R.id.book_detail_author_summary)
    TextView mBookAuthorSummary; // 作者简介
    @BindView(R.id.book_detail_summary)
    TextView mBookSummary; // 书籍简介
    @BindView(R.id.book_detail_catalog)
    TextView mBookCatalog; // 目录

    private String mUserId;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void init() {
        // init toolbar
        setToolbar(getString(R.string.book_detail_title), true);
        // init data
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");
        mUserId = intent.getStringExtra("userId");
        // get data
        getDetail(bookId);
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

    public void onGetDataSuccess(BookDetailData data) {
        setData(data);
    }

    public void onGetDataError(String error) {
        BaseToast.toast(this, error);
    }

    public void onGetCollectionSuccess(BookCollectionData data) {
        setData(data.getBook());
    }

    public void onGetCollectionError(String error) {
        BaseToast.toast(this, error);
    }

    /**
     * 获取书籍详情
     *
     * @param bookId 书籍 Id
     */
    private void getDetail(String bookId) {
        if (TextUtils.isEmpty(mUserId)) {
            BookClient.getInstance().getBookDetail(bookId, new IResponse<BookDetailData>() {

                @Override
                public void onResponse(BookDetailData result) {
                    onGetDataSuccess(result);
                }

                @Override
                public void onError(int code, String reason) {
                    onGetDataError(reason);
                }

            });
        } else {
            BookClient.getInstance().getBookDetail(bookId, mUserId, new IResponse<BookCollectionData>() {

                @Override
                public void onResponse(BookCollectionData result) {
                    onGetCollectionSuccess(result);
                }

                @Override
                public void onError(int code, String reason) {
                    onGetCollectionError(reason);
                }

            });
        }
    }

}
