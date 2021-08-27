package com.yhkhgl.top.bean;

import java.io.Serializable;
import java.util.List;

public class TwoFragmentAllBean implements Serializable {
    public String id;
    public String name;
    public String tags;
    public String quota; //额度
    public String interest_rate;  //利率
    public String cycle;   //周期
    public String repayment_method;
    public String fraction;
    public int is_attestation; //0不显示图标 、1显示
    public String address;
    public String longitude;
    public String latitude;
    public String contacts;
    public String content_body;
    public String collect_num;
    public String hot;
    public String sort;
    public String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getIs_attestation() {
        return is_attestation;
    }

    public void setIs_attestation(int is_attestation) {
        this.is_attestation = is_attestation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContent_body() {
        return content_body;
    }

    public void setContent_body(String content_body) {
        this.content_body = content_body;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTag_arr() {
        return tag_arr;
    }

    public void setTag_arr(List<String> tag_arr) {
        this.tag_arr = tag_arr;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public List<String> tag_arr;
    public String collect;
}
