package com.binwang.bean.shop;

/**
 * Created by owen on 17/6/7.
 */
public class ShopResource {
    private long id;
    private long shopId;
    private String imgUrl;

    public ShopResource() {
    }

    public ShopResource(long shopId, String imgUrl) {
        this.shopId = shopId;
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
