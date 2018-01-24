package com.binwang.service;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;

import java.util.List;

/**
 * Created by yy
 */
public interface ActivityService {
    Boolean add(ActParam actParam);
    Boolean regitemadd(RegItemListModel regItemListModel);
    List<ActListModel> list(int curPage, int pageSum, String name,String username, String begin, String end);
    int listSum(String name,String username, String begin, String end);
    List<RegItemListModel> regitemlist(int curPage, int pageSum,String username);
    int reglistSum(String username);
    List<RegItemListModel> actregitemlist(String username);

    ActParam get(long id);

    Boolean edit(ActParam actParam);
    Boolean regitemedit(RegItemListModel regItemListModel);
    Boolean delete(long id);
    Boolean regitemdelete(long id,String username);
    /*签到报名管理*/
    List<SignListModel> signList(int curPage, int pageSum, long actId, String code, int sign);
    //用户管理全部列表
    List<SignListModel> signListAll(long actId, String code, int sign);
    int signListSum(long actId, String code, int sign);

    Boolean doSign(long actId,String openId);
//    报名状态修改
    Boolean doReg(long actId,String openId);
}
