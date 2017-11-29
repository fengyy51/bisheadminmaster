package com.binwang.bean.shop;

/**
 * Created by owen on 17/6/7.
 */
public class ShopListModel {
    private long id;
    private String name;
    private int area;
    private int floor;

    public ShopListModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
