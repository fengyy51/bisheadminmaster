package com.binwang.bean.prize;

public class PrizeParam {
    private Long id;
    private String actName;
    private Long actId;
    private String begin;
    private String end;
    private int prizeNum;
    private String shareNum;
    private int prizeMaxNum;
    private String prizeDecoration;
    public PrizeParam(){}
    public PrizeParam(Long id,String actName,Long actId,String begin,String end,int prizeNum,int prizeMaxNum,String shareNum,String prizeDecoration){
        this.id=id;
        this.actName=actName;
        this.actId=actId;
        this.begin=begin;
        this.end=end;
        this.prizeNum=prizeNum;
        this.prizeMaxNum=prizeMaxNum;
        this.shareNum=shareNum;
        this.prizeDecoration=prizeDecoration;
    }

    public String getShareNum() {
        return shareNum;
    }

    public Long getActId() {
        return actId;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public int getPrizeMaxNum() {
        return prizeMaxNum;
    }

    public int getPrizeNum() {
        return prizeNum;
    }

    public String getPrizeDecoration() {
        return prizeDecoration;
    }

    public void setPrizeDecoration(String prizeDecoration) {
        this.prizeDecoration = prizeDecoration;
    }

    public void setPrizeMaxNum(int prizeMaxNum) {
        this.prizeMaxNum = prizeMaxNum;
    }

    public void setPrizeNum(int prizeNum) {
        this.prizeNum = prizeNum;
    }
}
