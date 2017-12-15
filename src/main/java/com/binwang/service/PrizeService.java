package com.binwang.service;

import com.binwang.bean.prize.PrizeModel;
import com.binwang.bean.prize.PrizeParam;

import java.util.List;

/**
 * Created by yy on 17/8/17.
 */
public interface PrizeService {
//    List<PrizeModel> getOneAndTwo(int collectId);

    List<PrizeModel> getThreeAndFour(int collectId);

    List<PrizeModel> getNoLimit(int collectId);

    List<PrizeModel>getList(String type,String actName, int curPage, int pageSum);
    int getListSum(String type,String actName);

    Boolean changeNum(String actName, int num, int id,String name,String type,int ratio,String info,String duijiangTime,String duijiangLoc);
    Boolean paramDelete(int id);
    List<String> getActName();
    List<String> getType();
    Boolean addPrizeParam(PrizeParam prizeParam);
}
