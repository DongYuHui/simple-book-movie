package com.kyletung.simplebookmovie.ui.book;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.commonlib.main.BaseActivity;
import com.kyletung.commonlib.utils.ImageLoader;
import com.kyletung.simplebookmovie.R;
import com.kyletung.simplebookmovie.client.request.BookClient;
import com.kyletung.simplebookmovie.data.book.BookSubject;
import com.kyletung.simplebookmovie.data.bookdetail.BookCollectionData;
import com.kyletung.simplebookmovie.data.bookdetail.BookDetailData;
import com.kyletung.simplebookmovie.ui.main.WebActivity;
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

    private static final String ENTRY_USER_ID = "entry_user_id";
    private static final String ENTRY_BOOK_SUBJECT = "entry_book_subject";

    // views
    @BindView(R.id.book_appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.book_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.book_image)
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
    private BookSubject mBookSubject;

    public static void start(Context context, BookSubject bookSubject, String userId) {
        Intent starter = new Intent(context, BookDetailActivity.class);
        starter.putExtra(ENTRY_BOOK_SUBJECT, bookSubject);
        starter.putExtra(ENTRY_USER_ID, userId);
        context.startActivity(starter);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initView() {
        // init data
        Intent intent = getIntent();
        mUserId = intent.getStringExtra(ENTRY_USER_ID);
        mBookSubject = (BookSubject) intent.getSerializableExtra(ENTRY_BOOK_SUBJECT);
        // init toolbar
        setToolbar(mBookSubject.getTitle(), true);
    }

    @Override
    protected void business() {
        getDetail(mBookSubject.getId());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_menu_web:
                WebActivity.start(this, mBookSubject.getTitle(), mBookSubject.getAlt());
                return true;
            case R.id.detail_menu_explorer:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(mBookSubject.getAlt());
                intent.setData(url);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mScrollView.getScrollY() != 0) {
                mScrollView.smoothScrollTo(0, 0);
                return true;
            }
            if (isAppBarLayoutCollapsed()) {
                mAppBarLayout.setExpanded(true, true);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * AppBarLayout 是否有折叠
     *
     * @return 是否有折叠
     */
    private boolean isAppBarLayoutCollapsed() {
        return (mAppBarLayout.getHeight() - mAppBarLayout.getBottom()) != 0;
    }

}
