package com.kyletung.simplebookmovie.event;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/07/14 at 21:59<br>
 * <br>
 * FixMe
 */
public class SearchEvent {

    private String content;

    public SearchEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SearchEvent{" +
                "content='" + content + '\'' +
                '}';
    }
}
