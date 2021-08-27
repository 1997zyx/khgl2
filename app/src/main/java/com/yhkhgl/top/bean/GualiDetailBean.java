package com.yhkhgl.top.bean;

import java.io.Serializable;

public class GualiDetailBean implements Serializable {
    private String id;
    private String user_customization_id;
    private String user_id;
    private String service_id;
    private String follow_date;
    private String visit_date;
    private String next_follow_date;
    private String content;

    private String transaction_amount;
    private String commission;
    private String transaction_plan;
    private String success_time;

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTransaction_plan() {
        return transaction_plan;
    }

    public void setTransaction_plan(String transaction_plan) {
        this.transaction_plan = transaction_plan;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_customization_id() {
        return user_customization_id;
    }

    public void setUser_customization_id(String user_customization_id) {
        this.user_customization_id = user_customization_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getFollow_date() {
        return follow_date;
    }

    public void setFollow_date(String follow_date) {
        this.follow_date = follow_date;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getNext_follow_date() {
        return next_follow_date;
    }

    public void setNext_follow_date(String next_follow_date) {
        this.next_follow_date = next_follow_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
