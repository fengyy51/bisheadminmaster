package com.binwang.bean.collect;

import java.util.List;

/**
 * Created by yy on 17/12/13.
 */
public class CDetailModel {
    private long id;
    private long collectId;
    private String regItem;
    private int isOk;

    public CDetailModel() {
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

    public String getRegItem() {
        return regItem;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }
    //    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getWechatId() {
//        return wechatId;
//    }
//
//    public void setWechatId(String wechatId) {
//        this.wechatId = wechatId;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getBrandName() {
//        return brandName;
//    }
//
//    public void setBrandName(String brandName) {
//        this.brandName = brandName;
//    }
//
//    public String getBrandImgUrl() {
//        return brandImgUrl;
//    }
//
//    public void setBrandImgUrl(String brandImgUrl) {
//        this.brandImgUrl = brandImgUrl;
//    }
//
//    public String getProductImgUrls() {
//        return productImgUrls;
//    }
//
//    public void setProductImgUrls(String productImgUrls) {
//        this.productImgUrls = productImgUrls;
//    }
//
//    public List<String> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<String> products) {
//        this.products = products;
//    }
//
//    public String getIntro() {
//        return intro;
//    }
//
//    public void setIntro(String intro) {
//        this.intro = intro;
//    }

    public int getIsOk() {
        return isOk;
    }

    public void setIsOk(int isOk) {
        this.isOk = isOk;
    }
}
