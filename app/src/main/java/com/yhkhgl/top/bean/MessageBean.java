package com.yhkhgl.top.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {
    private String id;
    private String title;
    private String time_msg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime_msg() {
        return time_msg;
    }

    public void setTime_msg(String time_msg) {
        this.time_msg = time_msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
