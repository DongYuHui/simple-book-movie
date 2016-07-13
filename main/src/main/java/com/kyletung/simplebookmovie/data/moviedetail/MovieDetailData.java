package com.kyletung.simplebookmovie.data.moviedetail;

import com.kyletung.simplebookmovie.data.Image;
import com.kyletung.simplebookmovie.data.movie.Rating;
import com.kyletung.simplebookmovie.data.movie.Staff;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/13 at 22:08<br>
 * <br>
 * FixMe
 */
public class MovieDetailData {

    private Rating rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private Image images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private String share_url;
    private String schedule_url;
    private ArrayList<String> countries;
    private ArrayList<String> genres;
    private int collect_count;
    private ArrayList<Staff> casts;
    private String original_title;
    private String summary;
    private String subtype;
    private ArrayList<Staff> directors;
    private int comments_count;
    private int ratings_count;
    private ArrayList<String> aka;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public ArrayList<Staff> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Staff> casts) {
        this.casts = casts;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public ArrayList<Staff> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Staff> directors) {
        this.directors = directors;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public ArrayList<String> getAka() {
        return aka;
    }

    public void setAka(ArrayList<String> aka) {
        this.aka = aka;
    }

    @Override
    public String toString() {
        return "MovieDetailData{" +
                "rating=" + rating +
                ", reviews_count=" + reviews_count +
                ", wish_count=" + wish_count +
                ", douban_site='" + douban_site + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", title='" + title + '\'' +
                ", share_url='" + share_url + '\'' +
                ", schedule_url='" + schedule_url + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                ", collect_count=" + collect_count +
                ", casts=" + casts +
                ", original_title='" + original_title + '\'' +
                ", summary='" + summary + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", comments_count=" + comments_count +
                ", ratings_count=" + ratings_count +
                ", aka=" + aka +
                '}';
    }

}
