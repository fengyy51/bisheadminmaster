package com.binwang.bean.prize;

public class PrizeParam {
    private String username;//系统的使用者
    private Long id;
    private String name;
    private Long actId;
    private String begin;
    private String end;
    private String code;
    private int prizeNum;
    private String shareNum;
    private int prizeMaxNum;
    private String prizeDecoration;
    private String topImg;
    private String prizelistImg;
    private String color;//背景颜色
    public PrizeParam(){}
    public PrizeParam(Long id,String name,String username,Long actId,String begin,String end,String code,int prizeNum,int prizeMaxNum,String shareNum,String prizeDecoration,String topImg,String prizelistImg,String color){
        this.id=id;
        this.name=name;
        this.username=username;
        this.actId=actId;
        this.begin=begin;
        this.end=end;
        this.code=code;
        this.prizeNum=prizeNum;
        this.prizeMaxNum=prizeMaxNum;
        this.shareNum=shareNum;
        this.prizeDecoration=prizeDecoration;
        this.topImg=topImg;
        this.prizelistImg=prizelistImg;
        this.color=color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrizelistImg() {
        return prizelistImg;
    }

    public void setPrizelistImg(String prizelistImg) {
        this.prizelistImg = prizelistImg;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
