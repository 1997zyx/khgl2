package com.yhkhgl.top.bean;

import java.io.Serializable;

public class OnFragmenBannerBean implements Serializable{
    private String id;
    private String img_url;
    private String sort;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    //    private String
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
