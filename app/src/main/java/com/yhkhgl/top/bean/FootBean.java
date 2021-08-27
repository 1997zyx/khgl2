package com.yhkhgl.top.bean;

import java.io.Serializable;

public class FootBean implements Serializable {
    private String id;
    private String product_id;
    private String visit_date;
    private String name;
    private String quota;
    private String interest_rate;
    private String cycle;
    private String views;
    private String fraction;
    private String is_attestation;
    private String status;
    private boolean isxianshi=false;


    private String h_name;
    private String h_tags;
    private String h_price;
    private String h_total_price;
    private String h_measure;
    private String h_lc;
    private String category_id;//1贷款产品  2出租产品
    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }



    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_tags() {
        return h_tags;
    }

    public void setH_tags(String h_tags) {
        this.h_tags = h_tags;
    }

    public String getH_price() {
        return h_price;
    }

    public void setH_price(String h_price) {
        this.h_price = h_price;
    }

    public String getH_total_price() {
        return h_total_price;
    }

    public void setH_total_price(String h_total_price) {
        this.h_total_price = h_total_price;
    }

    public String getH_measure() {
        return h_measure;
    }

    public void setH_measure(String h_measure) {
        this.h_measure = h_measure;
    }

    public String getH_lc() {
        return h_lc;
    }

    public void setH_lc(String h_lc) {
        this.h_lc = h_lc;
    }

    public boolean isIsxianshi() {
        return isxianshi;
    }

    public void setIsxianshi(boolean isxianshi) {
        this.isxianshi = isxianshi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getIs_attestation() {
        return is_attestation;
    }

    public void setIs_attestation(String is_attestation) {
        this.is_attestation = is_attestation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
