package com.binwang.bean.votedata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 18/5/14
 */
public class VoteParam {
    private long id;
    private long actId;
    private String content;
    private List<String> voteDataImgs = new ArrayList<>();
//    private Boolean voteDataImgsChange;

    public VoteParam() {
    }


//    public Boolean getvoteDataImgsChange() {
//        return voteDataImgsChange;
//    }
//
//    public void setvoteDataImgsChange(Boolean voteDataImgsChange) {
//        this.voteDataImgsChange = voteDataImgsChange;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public long getActId() {
        return actId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Boolean getVoteDataImgsChange() {
//        return voteDataImgsChange;
//    }
//
//    public void setVoteDataImgsChange(Boolean voteDataImgsChange) {
//        this.voteDataImgsChange = voteDataImgsChange;
//    }

    public List<String> getVoteDataImgs() {
        return voteDataImgs;
    }

    public void setVoteDataImgs(List<String> voteDataImgs) {
        this.voteDataImgs = voteDataImgs;
    }
}
