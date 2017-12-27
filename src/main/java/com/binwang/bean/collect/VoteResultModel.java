package com.binwang.bean.collect;

public class VoteResultModel {
    private long voteNum;
    private long id;
    private long itemId;
    private long actId;

    public VoteResultModel(){}
    public VoteResultModel(long id,long voteNum,long itemId,long actId){
        this.id=id;
        this.voteNum=voteNum;
        this.itemId=itemId;
        this.actId=actId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setVoteNum(long voteNum) {
        this.voteNum = voteNum;
    }

    public long getActId() {
        return actId;
    }

    public long getId() {
        return id;
    }

    public long getItemId() {
        return itemId;
    }

    public long getVoteNum() {
        return voteNum;
    }
}
