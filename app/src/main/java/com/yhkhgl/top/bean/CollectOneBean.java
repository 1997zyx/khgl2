package com.yhkhgl.top.bean;

import java.io.Serializable;

public class CollectOneBean implements Serializable {
    private String id;
    private String product_id;
    private String name;
    private String tags;
    private String quota;
    private String interest_rate;
    private String cycle;
    private String repayment_method;
    private String fraction;
    private String is_attestation;
    private String status;
    private String collect_num;
    private String cart_status;//1已添加对比  0未添加

    public String getCart_status() {
        return cart_status;
    }

    public void setCart_status(String cart_status) {
        this.cart_status = cart_status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public String getRepayment_method() {
        return repayment_method;
    }

    public void setRepayment_method(String repayment_method) {
        this.repayment_method = repayment_method;
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

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }
}
