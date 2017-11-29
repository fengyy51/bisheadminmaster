package com.binwang.service;

import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;

import java.util.List;

/**
 * Created by owen on 17/7/13.
 */
public interface CollectService {
    List<CListModel> getList(long collectId, int type, int curPage, int pageSum);

    CDetailModel getDetail(long collectId, long itemId);

    Boolean handleApprove(long collectId, long itemId, int type);

    int getListSum(long collectId, int type);

    int getApproveSum(long collectId);

    Boolean firstImgUpdate(long id,String url);

    Boolean detailImgUpdate(long id, String urls);

}
