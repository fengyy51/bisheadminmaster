package com.binwang.bean.activity;

public class VoteParam
{
    private Long id;
    private String username;
    private String actName;
    private Long actId;
    private String begin;
    private String end;
    private int proNum;
    private int voteNum;
    private String shareNum;
    private int voteMaxNum;
    private String voteDecoration;
    private int proApproved;
    public VoteParam(){}
    public VoteParam(Long id,String username,String actName,Long actId,String begin,String end,int proNum,int voteNum,int voteMaxNum,String shareNum,String voteDecoration,int proApproved){
        this.id=id;
        this.username=username;
        this.actName=actName;
        this.actId=actId;
        this.begin=begin;
        this.end=end;
        this.proNum=proNum;
        this.voteNum=voteNum;
        this.voteMaxNum=voteMaxNum;
        this.shareNum=shareNum;
        this.voteDecoration=voteDecoration;
        this.proApproved=proApproved;
    }

    public int getProNum() {
        return proNum;
    }

    public String getShareNum() {
        return shareNum;
    }

    public int getVoteNum() {
        return voteNum;
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

    public String getVoteDecoration() {
        return voteDecoration;
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

    public void setProNum(int proNum) {
        this.proNum = proNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public void setVoteDecoration(String voteDecoration) {
        this.voteDecoration = voteDecoration;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public int getVoteMaxNum() {
        return voteMaxNum;
    }

    public void setVoteMaxNum(int voteMaxNum) {
        this.voteMaxNum = voteMaxNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getProApproved() {
        return proApproved;
    }

    public void setProApproved(int proApproved) {
        this.proApproved = proApproved;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
