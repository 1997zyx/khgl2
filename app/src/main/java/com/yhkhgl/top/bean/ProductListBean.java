package com.yhkhgl.top.bean;

import java.io.Serializable;
import java.util.List;

public class ProductListBean implements Serializable {
    private String id;
    private String name;
    private String tags;
    private String quota; //额度
    private String interest_rate;  //利率
    private String cycle;   //周期
    private String repayment_method;
    private String fraction;
    private int is_attestation; //0不显示图标 、1显示
    private String address;
    private String longitude;
    private String latitude;
    private String contacts;
    private String content_body;
    private String collect_num;
    private String hot;
    private String sort;
    private String status;
    private String cart_status;//1已添加对比  0未添加

    public String getCart_status() {
        return cart_status;
    }

    public void setCart_status(String cart_status) {
        this.cart_status = cart_status;
    }

    private String category_id;//1贷款产品  2出租产品
    private String h_name;
    private String h_tags;
    private String h_price;
    private String h_total_price;
    private String h_measure;
    private String h_lc;
    private String h_contacts;
    private String h_mobile;
    private String h_position;
    private String h_lon_lat;
    private String h_dfl;
    private String h_company;
    private String h_other;
    private String h_recommend;
    private String h_fraction;
    private String h_sort;
    private String h_attestation;
    private String h_status;
    private String h_pic;
    private String city_id;
    private String collect;

    private String product_id;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

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

    public String getH_contacts() {
        return h_contacts;
    }

    public void setH_contacts(String h_contacts) {
        this.h_contacts = h_contacts;
    }

    public String getH_mobile() {
        return h_mobile;
    }

    public void setH_mobile(String h_mobile) {
        this.h_mobile = h_mobile;
    }

    public String getH_position() {
        return h_position;
    }

    public void setH_position(String h_position) {
        this.h_position = h_position;
    }

    public String getH_lon_lat() {
        return h_lon_lat;
    }

    public void setH_lon_lat(String h_lon_lat) {
        this.h_lon_lat = h_lon_lat;
    }

    public String getH_dfl() {
        return h_dfl;
    }

    public void setH_dfl(String h_dfl) {
        this.h_dfl = h_dfl;
    }

    public String getH_company() {
        return h_company;
    }

    public void setH_company(String h_company) {
        this.h_company = h_company;
    }

    public String getH_other() {
        return h_other;
    }

    public void setH_other(String h_other) {
        this.h_other = h_other;
    }

    public String getH_recommend() {
        return h_recommend;
    }

    public void setH_recommend(String h_recommend) {
        this.h_recommend = h_recommend;
    }

    public String getH_fraction() {
        return h_fraction;
    }

    public void setH_fraction(String h_fraction) {
        this.h_fraction = h_fraction;
    }

    public String getH_sort() {
        return h_sort;
    }

    public void setH_sort(String h_sort) {
        this.h_sort = h_sort;
    }

    public String getH_attestation() {
        return h_attestation;
    }

    public void setH_attestation(String h_attestation) {
        this.h_attestation = h_attestation;
    }

    public String getH_status() {
        return h_status;
    }

    public void setH_status(String h_status) {
        this.h_status = h_status;
    }

    public String getH_pic() {
        return h_pic;
    }

    public void setH_pic(String h_pic) {
        this.h_pic = h_pic;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }





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

    private List<String> tag_arr;
    private List<String> h_tag_arr;

    public List<String> getH_tag_arr() {
        return h_tag_arr;
    }

    public void setH_tag_arr(List<String> h_tag_arr) {
        this.h_tag_arr = h_tag_arr;
    }
}
