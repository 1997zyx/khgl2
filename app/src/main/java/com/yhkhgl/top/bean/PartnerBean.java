package com.yhkhgl.top.bean;

import java.io.Serializable;
import java.util.List;

public class PartnerBean implements Serializable {
    private String id;
    private String user_name;
    private String user_avatar;
    private String service_scope;
    private String labels;
    private String collect_num;
    private String service_address;
    private String lon_lat;
    private String company_profile;
    private String official_certification;
    private String good_service;
    private String industry_leading;
    private String home_recommend;
    private String users_number;
    private String sort;
    private String state;
    private String longitude;
    private String latitude;
    private String distance;

    public List<String> getLabel_arr() {
        return label_arr;
    }

    public void setLabel_arr(List<String> label_arr) {
        this.label_arr = label_arr;
    }

    private List<String> label_arr;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getService_scope() {
        return service_scope;
    }

    public void setService_scope(String service_scope) {
        this.service_scope = service_scope;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getService_address() {
        return service_address;
    }

    public void setService_address(String service_address) {
        this.service_address = service_address;
    }

    public String getLon_lat() {
        return lon_lat;
    }

    public void setLon_lat(String lon_lat) {
        this.lon_lat = lon_lat;
    }

    public String getCompany_profile() {
        return company_profile;
    }

    public void setCompany_profile(String company_profile) {
        this.company_profile = company_profile;
    }

    public String getOfficial_certification() {
        return official_certification;
    }

    public void setOfficial_certification(String official_certification) {
        this.official_certification = official_certification;
    }

    public String getGood_service() {
        return good_service;
    }

    public void setGood_service(String good_service) {
        this.good_service = good_service;
    }

    public String getIndustry_leading() {
        return industry_leading;
    }

    public void setIndustry_leading(String industry_leading) {
        this.industry_leading = industry_leading;
    }

    public String getHome_recommend() {
        return home_recommend;
    }

    public void setHome_recommend(String home_recommend) {
        this.home_recommend = home_recommend;
    }

    public String getUsers_number() {
        return users_number;
    }

    public void setUsers_number(String users_number) {
        this.users_number = users_number;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
//    private List<DataBean> label_arr;
//    public class DataBean{
//        public
//    }
}
