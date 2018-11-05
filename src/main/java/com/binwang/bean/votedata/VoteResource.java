package com.binwang.bean.votedata;

/**
 * Created by yy on 18/5/10.
 */
public class VoteResource {
    private long id;
    private long voteId;
    private String imgUrl;

    public VoteResource() {
    }

    public VoteResource(long voteId, String imgUrl) {
        this.voteId = voteId;
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return voteId;
    }

    public void setShopId(long shopId) {
        this.voteId = shopId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
