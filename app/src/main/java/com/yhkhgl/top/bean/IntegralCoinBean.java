package com.yhkhgl.top.bean;

import java.io.Serializable;

public class IntegralCoinBean implements Serializable {
    private String id;
    private String name;
    private String type;
    private String days;
    private String pic;
    private String value_points;
    private String inventory_quantity;
    private String state;
    private String point_status;//积分状态
    private String quantity_status;//货物状态

    public String getPoint_status() {
        return point_status;
    }

    public void setPoint_status(String point_status) {
        this.point_status = point_status;
    }

    public String getQuantity_status() {
        return quantity_status;
    }

    public void setQuantity_status(String quantity_status) {
        this.quantity_status = quantity_status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getValue_points() {
        return value_points;
    }

    public void setValue_points(String value_points) {
        this.value_points = value_points;
    }

    public String getInventory_quantity() {
        return inventory_quantity;
    }

    public void setInventory_quantity(String inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
