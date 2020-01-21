package com.linz.model;

import java.sql.Timestamp;

public class Article {
    private Long article_id;
    private Long user_id;
    private String article_title;
    private String article_content;
    private Long article_views;
    private Long article_comment_count;
    private Timestamp article_date;
    private Long article_like_count;

    public Long getArticle_id() {
        return article_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public Long getArticle_views() {
        return article_views;
    }

    public Long getArticle_comment_count() {
        return article_comment_count;
    }

    public Timestamp getArticle_date() {
        return article_date;
    }

    public Long getArticle_like_count() {
        return article_like_count;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public void setArticle_views(Long article_views) {
        this.article_views = article_views;
    }

    public void setArticle_comment_count(Long article_comment_count) {
        this.article_comment_count = article_comment_count;
    }

    public void setArticle_date(Timestamp article_date) {
        this.article_date = article_date;
    }

    public void setArticle_like_count(Long article_like_count) {
        this.article_like_count = article_like_count;
    }
}
