package com.yhkhgl.top.bean;

import java.io.Serializable;

public class OnFragmenBean implements Serializable{
    private String id;
    private String type;
    private String content;
//    private String
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
