package com.binwang.service;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.collect.VoteListModel;
import com.binwang.bean.collect.VoteResultModel;

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

    //投票列表
    List<VoteListModel>listVote(int curPage, int pageSum, String actName, String username, String begin, String end);
    int listVoteSum(String actName,String username, String begin, String end);
    // 增加投票设置
    Boolean addVoteParam(VoteParam voteParam);
    //投票设置获取
    VoteParam VoteParamGet(long id);
    //投票设置编辑
    Boolean VoteParamEdit(VoteParam voteParam);
    //投票设置删除
    Boolean PrizeParamDelete(int id);
    //投票结果获取
    List<VoteResultModel> voteResult(int curPage,int pageSum,long itemId,long actId);
    int voteResultSum(long itemId,long actId);
    //投票结果数编辑
    Boolean VoteResultEdit(VoteResultModel voteResultModel);
    //获取投票活动名称
    List<String> getActName(String username);
    //刷票记录查询
    List<VoteResultModel> getBrushlist(int id,String actName,String begin,String end,int num);
    int getBrushlistSum(int id,String actName,String begin,String end,int num);
    List<Integer> getRecordIDS(String actName);
}
