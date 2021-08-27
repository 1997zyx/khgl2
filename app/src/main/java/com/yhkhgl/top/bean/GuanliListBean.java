package com.yhkhgl.top.bean;

import java.io.Serializable;

public class GuanliListBean implements Serializable {
    private String id;
    private String surname;
    private String mobile;
    private String key_categories;
    private String channel;
    private String follow_status;
    private String next_s_date;
    private String cus_info;
    private String credit_inquiry;

    public String[] getCus_type_arr() {
        return cus_type_arr;
    }

    public void setCus_type_arr(String[] cus_type_arr) {
        this.cus_type_arr = cus_type_arr;
    }

    private String[] cus_type_arr;
    public String getCredit_inquiry() {
        return credit_inquiry;
    }

    public void setCredit_inquiry(String credit_inquiry) {
        this.credit_inquiry = credit_inquiry;
    }

    public String getCus_info() {
        return cus_info;
    }

    public void setCus_info(String cus_info) {
        this.cus_info = cus_info;
    }

    public String getNext_s_date() {
        return next_s_date;
    }

    public void setNext_s_date(String next_s_date) {
        this.next_s_date = next_s_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKey_categories() {
        return key_categories;
    }

    public void setKey_categories(String key_categories) {
        this.key_categories = key_categories;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(String follow_status) {
        this.follow_status = follow_status;
    }
}
