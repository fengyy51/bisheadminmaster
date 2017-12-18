package com.binwang.service;

import com.binwang.bean.prize.PrizeListModel;
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
    //抽奖活动列表
    List<PrizeListModel> list(int curPage, int pageSum, String name, String username, String begin, String end);
    int listSum(String name,String username, String begin, String end);
//奖品数据列表
    List<PrizeModel>getList(String type,String actName, int curPage, int pageSum);
    int getListSum(String type,String actName);

    Boolean changeNum(String actName, int num, int id,String name,String type,int ratio,String info,String duijiangTime,String duijiangLoc);
    Boolean paramDelete(int id);
    List<String> getActName();
    List<String> getType();
    //抽奖配置
    Boolean addPrizeParam(PrizeParam prizeParam);
    PrizeParam getPrizeParam(int id);
    Boolean editPrizeParam(PrizeParam prizeParam);
    Boolean deletePrizeParam(int id);

}
