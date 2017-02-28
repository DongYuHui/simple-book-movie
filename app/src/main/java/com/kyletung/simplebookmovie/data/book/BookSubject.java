package com.kyletung.simplebookmovie.data.book;

import com.kyletung.simplebookmovie.data.Image;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/07 at 18:28<br>
 * <br>
 * FixMe
 */
public class BookSubject implements Serializable {

    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String ebook_url;
    private String pages;
    private Image images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String ebook_price;
    private String price;
    private Series series;

    private ArrayList<String> translator;
    private ArrayList<Tag> tags;
    private ArrayList<String> author;
    private Rating rating;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEbook_url() {
        return ebook_url;
    }

    public void setEbook_url(String ebook_url) {
        this.ebook_url = ebook_url;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEbook_price() {
        return ebook_price;
    }

    public void setEbook_price(String ebook_price) {
        this.ebook_price = ebook_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public ArrayList<String> getTranslator() {
        return translator;
    }

    public void setTranslator(ArrayList<String> translator) {
        this.translator = translator;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "BookSubject{" +
                "subtitle='" + subtitle + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", origin_title='" + origin_title + '\'' +
                ", image='" + image + '\'' +
                ", binding='" + binding + '\'' +
                ", catalog='" + catalog + '\'' +
                ", ebook_url='" + ebook_url + '\'' +
                ", pages='" + pages + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", alt_title='" + alt_title + '\'' +
                ", author_intro='" + author_intro + '\'' +
                ", summary='" + summary + '\'' +
                ", ebook_price='" + ebook_price + '\'' +
                ", price='" + price + '\'' +
                ", series=" + series +
                ", translator=" + translator +
                ", tags=" + tags +
                ", author=" + author +
                ", rating=" + rating +
                '}';
    }

}
