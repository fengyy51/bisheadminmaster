package com.binwang.service.impl;

import com.binwang.bean.prize.PrizeListModel;
import com.binwang.bean.prize.PrizeModel;
import com.binwang.bean.prize.PrizeParam;
import com.binwang.bean.prize.PrizeUserModel;
import com.binwang.dao.IPrizeDao;
import com.binwang.exception.UserException;
import com.binwang.service.PrizeService;
import com.binwang.util.excelToSql.bean.Prize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen on 17/8/17.
 */
@Service
public class PrizeServiceImpl implements PrizeService {

    @Resource
    private IPrizeDao prizeDao;

    @Value("${binwang.prize.nolimit.ids}")
    private String IDS;

    private final int ONESUM = 10;
    private final int TWOSUM = 75;
    private final String ONENAME = "500元现金券";
    private final String TWONAME = "100元现金券";


//    @Override
//    public List<PrizeModel> getOneAndTwo(int collectId) {
//        int oneNum = ONESUM - prizeDao.oneTotal(collectId);
//        int twoNum = TWOSUM - prizeDao.twoTotal(collectId);
//        List<PrizeModel> l = new ArrayList<>();
//        PrizeModel p1 = new PrizeModel(1, "一等奖", ONENAME, oneNum);
//        PrizeModel p2 = new PrizeModel(2, "二等奖", TWONAME, twoNum);
//        l.add(p1);
//        l.add(p2);
//        return l;
//    }

    @Override
    public List<PrizeModel> getThreeAndFour(int collectId) {
        return prizeDao.threeAndFourPrize(collectId);
    }

    @Override
    public List<PrizeModel> getNoLimit(int collectId) {
        List<Integer> lIds = new ArrayList<>();
        String[] idArray = IDS.split(",");
        for (int i = 0; i < idArray.length; i++) {
            lIds.add(Integer.parseInt(idArray[i]));
        }
        return prizeDao.getNoLimitPrize(lIds, collectId);
    }
    //抽奖活动列表
    @Override
    public List<PrizeListModel> list(int curPage, int pageSum, String name,String username, String begin, String end) {
        try {
            List<PrizeListModel> res = prizeDao.listPrize(name, username,begin, end, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            throw new UserException("获取抽奖活动列表失败！");
        }
    }
    @Override
    public int listSum(String name,String username, String begin, String end) {
        try {
            return prizeDao.listPrizeSum(name,username ,begin, end);
        } catch (Exception e) {
            throw new UserException("获取抽奖活动列表数量失败!");
        }
    }
    @Override
    @Transactional
    public List<PrizeModel>getList(String name,String type,String actName, int curPage, int pageSum){
        return prizeDao.getList(name,type,actName,(curPage - 1) * pageSum, pageSum);
    }
    @Override
    @Transactional
    public int getListSum(String name,String type,String actName){
        return prizeDao.getListSum(name,type,actName);
    }

    @Override
    @Transactional
    public Boolean changeNum(String actName, int num, int id,String name,String type,int ratio,String info,String duijiangTime,String duijiangLoc) {
//        String[] idArray = IDS.split(",");
//        Boolean flag = false;
//        for (int i = 0; i < idArray.length; i++) {
//            if (Integer.parseInt(idArray[i]) == id)
//                flag = true;
//        }
//        if (flag) {
            //id在不限量内可以修改
            if (prizeDao.updateNum(num, id,actName,name,type,ratio,info,duijiangTime,duijiangLoc) > 0)
                return true;
            else
                return false;
//        } else
//            return false;
    }

    @Override
    @Transactional
    public Boolean paramDelete(int id){
        if(prizeDao.paramDelete(id)>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    @Transactional
    public List<String> getActName(String username){
        return prizeDao.getActName(username);
    }
    @Override
    @Transactional
    public List<String> getType(){
        return prizeDao.getType();
    }
    @Override
    @Transactional
    public Boolean addPrizeParam(PrizeParam prizeParam){
        if(prizeDao.addPrizeParam(prizeParam)>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    @Transactional
    public PrizeParam getPrizeParam(int id){
        return prizeDao.getPrizeParam(id);
    }
    @Override
    @Transactional
    public Boolean editPrizeParam(PrizeParam prizeParam){
        if(prizeDao.editPrizeParam(prizeParam)>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    @Transactional
    public Boolean deletePrizeParam(int id){
        if(prizeDao.deletePrizeParam(id)>0){
            return true;
        }else{
            return false;
        }
    }
    //抽奖活动用户列表获取
    @Override
    @Transactional
    public List<PrizeUserModel>  userList(int curPage, int pageSum, long actId, String code, int isUse){
        try {
            List<PrizeUserModel> res = prizeDao.userList(actId, code, isUse, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            throw new UserException("获取抽奖用户列表失败！");
        }
    }
    @Override
    @Transactional
    public int userListSum(long actId, String code, int isUse){
        try {
            return prizeDao.userListSum(actId, code, isUse);
        } catch (Exception e) {
            throw new UserException("获取抽奖用户列表数量失败!");
        }
    }
    @Override
    @Transactional
    public Boolean doUse(long id){
        try {
            if(prizeDao.doUse(id)>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            throw new UserException("验证使用失败!");
        }
    }
}
