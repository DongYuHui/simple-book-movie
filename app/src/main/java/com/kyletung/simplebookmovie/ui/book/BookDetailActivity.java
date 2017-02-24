package com.kyletung.simplebookmovie.ui.book;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.utils.BlurUtil;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.book_cover)
    ImageView mBookCover; // 封面
    @BindView(R.id.cover_blur_background)
    ImageView mBookCoverBlur; // 模糊的封面背景
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
    protected void initView() {
        // init toolbar
        setToolbar(getString(R.string.book_detail_title), true);
    }

    @Override
    protected void business() {
        // init data
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");
        mUserId = intent.getStringExtra("userId");
        // get data
        getDetail(bookId);
    }

    private void setData(BookDetailData data) {
        ImageLoader.load(this, mBookCover, data.getImages().getLarge());
        setCoverBlurBackground(data.getImages().getSmall());
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

    public void onGetCollectionSuccess(BookCollectionData data) {
        setData(data.getBook());
    }

    /**
     * 获取书籍详情
     *
     * @param bookId 书籍 Id
     */
    private void getDetail(String bookId) {
        showProgress(getString(R.string.common_get_data), true, null);
        if (TextUtils.isEmpty(mUserId)) {
            BookClient.getInstance().getBookDetail(bookId).subscribe(newSubscriber(new Action1<BookDetailData>() {
                @Override
                public void call(BookDetailData bookDetailData) {
                    stopProgress();
                    onGetDataSuccess(bookDetailData);
                }
            }));
        } else {
            BookClient.getInstance().getBookDetail(bookId, mUserId).subscribe(newSubscriber(new Action1<BookCollectionData>() {
                @Override
                public void call(BookCollectionData bookCollectionData) {
                    stopProgress();
                    onGetCollectionSuccess(bookCollectionData);
                }
            }));
        }
    }

    private void setCoverBlurBackground(String url) {
        Observable.just(url).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                return BlurUtil.blurFromUrl(BookDetailActivity.this, url, 60, 90, 16);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                mBookCoverBlur.setImageBitmap(bitmap);
            }
        });
    }

}
