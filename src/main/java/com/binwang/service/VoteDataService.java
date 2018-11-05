package com.binwang.service;

import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;

import java.util.List;

public interface VoteDataService {
    Boolean add(String username,long actId,String content,List<String>voteDataImgs);

    List<VoteListModel> list(String username,String content,String actId,int curPage, int pageSum);

    int listSum(String username,String content,String actId);

    VoteParam get(long id);

    Boolean edit(VoteParam voteParam);

    Boolean delete(long id);
}
