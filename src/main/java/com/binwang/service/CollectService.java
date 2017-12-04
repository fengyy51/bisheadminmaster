package com.binwang.service;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;

import java.util.List;

/**
 * Created by yy on 17/12/13.
 */
public interface CollectService {
    List<CListModel> getList(long collectId, int type,String openId, int curPage, int pageSum);

    CDetailModel getDetail(long collectId, long itemId);

    Boolean handleApprove(long collectId, long itemId, int type);

    int getListSum(long collectId, int type,String openId);

    int getApproveSum(long collectId);

    Boolean firstImgUpdate(long id,String url);

    Boolean detailImgUpdate(long id, String urls);
//    增加投票设置
    Boolean addVoteParam(VoteParam voteParam);

}
