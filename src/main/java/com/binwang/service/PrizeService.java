package com.binwang.service;

import com.binwang.bean.prize.PrizeModel;

import java.util.List;

/**
 * Created by yy on 17/8/17.
 */
public interface PrizeService {
//    List<PrizeModel> getOneAndTwo(int collectId);

    List<PrizeModel> getThreeAndFour(int collectId);

    List<PrizeModel> getNoLimit(int collectId);

    List<PrizeModel>getList(String type,int relationId, int curPage, int pageSum);
    int getListSum(String type,int relationId);

    Boolean changeNum(int collectId, int num, int id,String name,String type,int ratio,String info,String duijiangTime,String duijiangLoc);
    Boolean paramDelete(int id);
    List<Integer> getRelationId();
    List<String> getType();
}
