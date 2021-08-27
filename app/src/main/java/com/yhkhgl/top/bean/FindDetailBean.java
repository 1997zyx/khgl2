package com.yhkhgl.top.bean;

import java.io.Serializable;

public class FindDetailBean implements Serializable {
    private String id;
    private String article_id;
    private String user_id;
    private String like_num;
    private String dislike_num;
    private String user_name;
    private String user_avatar;
    private String like;
    private String time_msg;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(String dislike_num) {
        this.dislike_num = dislike_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getTime_msg() {
        return time_msg;
    }

    public void setTime_msg(String time_msg) {
        this.time_msg = time_msg;
    }
}
