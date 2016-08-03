package com.kyletung.simplebookmovie.data.movie;

import com.kyletung.simplebookmovie.data.Image;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/02 at 17:53<br>
 * <br>
 * 电影列表中的实体类
 */
public class MovieSubject {

    private long id;
    private String alt;
    private String year;
    private String title;
    private String subtype;
    private int collect_count;
    private String original_title;

    private Image images;
    private Rating rating;
    private ArrayList<Staff> casts;
    private ArrayList<Staff> directors;
    private ArrayList<String> genres;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public ArrayList<Staff> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Staff> casts) {
        this.casts = casts;
    }

    public ArrayList<Staff> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Staff> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieSubject{" +
                "id=" + id +
                ", alt='" + alt + '\'' +
                ", year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", images=" + images +
                ", rating=" + rating +
                ", casts=" + casts +
                ", directors=" + directors +
                ", genres=" + genres +
                '}';
    }

}
