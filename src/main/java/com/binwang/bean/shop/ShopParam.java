package com.binwang.bean.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen on 17/6/7.
 */
public class ShopParam {
    private long id;
    private String name;
    private int area;
    private int floor;
    private String shopAbout;
    private String locAbout;
    private String keeperName;
    private String phone;
    private String qrCodeUrl;
    private String locAboutRelation;
    private List<String> shopImgs = new ArrayList<>();
    private Boolean shopImgsChange;

    public ShopParam() {
    }


    public Boolean getShopImgsChange() {
        return shopImgsChange;
    }

    public void setShopImgsChange(Boolean shopImgsChange) {
        this.shopImgsChange = shopImgsChange;
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

    public String getShopAbout() {
        return shopAbout;
    }

    public void setShopAbout(String shopAbout) {
        this.shopAbout = shopAbout;
    }

    public String getLocAbout() {
        return locAbout;
    }

    public void setLocAbout(String locAbout) {
        this.locAbout = locAbout;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getLocAboutRelation() {
        return locAboutRelation;
    }

    public void setLocAboutRelation(String locAboutRelation) {
        this.locAboutRelation = locAboutRelation;
    }

    public List<String> getShopImgs() {
        return shopImgs;
    }

    public void setShopImgs(List<String> shopImgs) {
        this.shopImgs = shopImgs;
    }

}
