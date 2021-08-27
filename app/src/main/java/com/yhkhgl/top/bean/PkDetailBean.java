package com.yhkhgl.top.bean;

import java.io.Serializable;

public class PkDetailBean implements Serializable {
    private String name;
    private String one_value;
    private String one_prominent;
    private String two_value;
    private String two_prominent;
    private String is_same;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOne_value() {
        return one_value;
    }

    public void setOne_value(String one_value) {
        this.one_value = one_value;
    }

    public String getOne_prominent() {
        return one_prominent;
    }

    public void setOne_prominent(String one_prominent) {
        this.one_prominent = one_prominent;
    }

    public String getTwo_value() {
        return two_value;
    }

    public void setTwo_value(String two_value) {
        this.two_value = two_value;
    }

    public String getTwo_prominent() {
        return two_prominent;
    }

    public void setTwo_prominent(String two_prominent) {
        this.two_prominent = two_prominent;
    }

    public String getIs_same() {
        return is_same;
    }

    public void setIs_same(String is_same) {
        this.is_same = is_same;
    }
}
