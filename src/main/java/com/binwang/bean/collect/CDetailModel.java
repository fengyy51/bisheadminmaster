package com.binwang.bean.collect;

import java.util.List;

/**
 * Created by owen on 17/7/13.
 */
public class CDetailModel {
    private long id;
    private long collectId;
    private String name;
    private String wechatId;
    private String mobile;
    private String brandName;
    private String brandImgUrl;
    private String productImgUrls;
    private List<String> products;
    private String intro;
    private int isOk;
    private String recUnit;

    public CDetailModel() {
    }

    public String getRecUnit() {
        return recUnit;
    }

    public void setRecUnit(String recUnit) {
        this.recUnit = recUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCollectId() {
        return collectId;
    }

    public void setCollectId(long collectId) {
        this.collectId = collectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImgUrl() {
        return brandImgUrl;
    }

    public void setBrandImgUrl(String brandImgUrl) {
        this.brandImgUrl = brandImgUrl;
    }

    public String getProductImgUrls() {
        return productImgUrls;
    }

    public void setProductImgUrls(String productImgUrls) {
        this.productImgUrls = productImgUrls;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getIsOk() {
        return isOk;
    }

    public void setIsOk(int isOk) {
        this.isOk = isOk;
    }
}
