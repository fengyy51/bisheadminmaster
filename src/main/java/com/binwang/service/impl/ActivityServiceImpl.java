package com.binwang.service.impl;

import com.binwang.bean.activity.*;
import com.binwang.dao.IActivityDao;
import com.binwang.exception.UserException;
import com.binwang.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yy on 17/5/11.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);


    @Resource
    private IActivityDao iActivityDao;


    @Override
    @Transactional
    //TODO:调研数据库批量插数据行
    public Boolean add(ActParam actParam) throws UserException {
        long id = actParam.getId();
        LOGGER.info(Long.toString(id));
        int res = iActivityDao.addAct(actParam);
        if (res > 0) {
            String content = actParam.getContent();
//            if (StringUtils.isEmpty(content))
//                return true;
            ActResource actResource = new ActResource(actParam.getId(), 1, content);
            if (iActivityDao.addResource(actResource) > 0)
                return true;
            else {
                LOGGER.error("活动附表记录添加失败，活动id为：" + id);
                throw new UserException("附表记录添加失败");
            }
        } else {
            LOGGER.error("活动主表记录添加失败，活动id为：" + id);
            throw new UserException("主表记录添加失败");
        }
    }
    @Override
    @Transactional
    public Boolean regitemadd(RegItemListModel regItemListModel)throws UserException{
        String username=regItemListModel.getUsername();
        long id = iActivityDao.regitemListSum(username)+1;
        LOGGER.info(Long.toString(id));
        int res = iActivityDao.regitemAdd(regItemListModel);
        if (res > 0) {
            return true;
        } else {
            LOGGER.error("报名项添加失败，活动id为：" + id);
            throw new UserException("报名项添加失败");
        }
    }
    @Override
    public List<ActListModel> list(int curPage, int pageSum, String name,String username, String begin, String end) {
        try {
            List<ActListModel> res = iActivityDao.listAct(name, username,begin, end, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            throw new UserException("获取活动列表失败！");
        }
    }
    @Override
    public int listSum(String name,String username, String begin, String end) {
        try {
            return iActivityDao.listActSum(name,username ,begin, end);
        } catch (Exception e) {
            throw new UserException("获取活动列表数量失败!");
        }
    }
    @Override
    public List<RegItemListModel> regitemlist(int curPage, int pageSum,String username){
        try {
            List<RegItemListModel> res = iActivityDao.regitemList((curPage - 1) * pageSum, pageSum,username);
            return res;
        } catch (Exception e) {
            throw new UserException("获取报名项列表失败！");
        }
    }
    @Override
    public int reglistSum(String username) {
        try {
            return iActivityDao.regitemListSum(username);
        } catch (Exception e) {
            throw new UserException("获取报名项列表数量失败!");
        }
    }
    @Override
    public List<RegItemListModel> actregitemlist(String username){
        try {
            List<RegItemListModel> res = iActivityDao.actregitemList(username);
            return res;
        } catch (Exception e) {
            throw new UserException("获取报名项列表失败！");
        }
    }


    @Override
    public ActParam get(long id) {
        try {
            ActParam actParam = iActivityDao.findById(id);
            List<ActResource> resList = iActivityDao.findByActId(id);
            for (ActResource actResource : resList) {
                if (actResource.getType() == 1) {
                    actParam.setContent(actResource.getContent());
                    break;
                }
            }
            return actParam;
        } catch (Exception e) {
            throw new UserException("查询活动详情失败！");
        }
    }


    @Override
    @Transactional
    public Boolean edit(ActParam actParam) {
        Boolean isContentChange = actParam.getContentChange();
        long id = actParam.getId();
        int res = iActivityDao.edit(actParam);
        if (res > 0) {
            //判断后删除附表
            if (isContentChange) {
                if (iActivityDao.updateActRes(id, 1, actParam.getContent()) > 0)
                    return true;
                else {
                    LOGGER.error("活动附表记录修改失败，活动id为：" + id);
                    throw new UserException("修改活动附表失败！");
                }
            }
            return true;
        } else {
            LOGGER.error("活动主表记录修改失败，活动id为：" + id);
            throw new UserException("修改活动主表失败！");
        }
    }
    @Override
    @Transactional
    public Boolean regitemedit(RegItemListModel regItemListModel){
        long id = regItemListModel.getId();
        String title=regItemListModel.getTitle();
        String dtype=regItemListModel.getDtype();
        String ifneed=regItemListModel.getIfneed();
        String options=regItemListModel.getOptions();
        LOGGER.info(Long.toString(id));
        int res = iActivityDao.regitemEdit(id,title,dtype,ifneed,options);
        if (res > 0) {
            return true;
        }else {
            LOGGER.error("报名项修改失败，报名项id为："+id);
            throw new UserException("修改报名项失败！");
        }
    }
    @Override
    @Transactional
    public Boolean delete(long id) {
        try {
            int res1 = iActivityDao.delete(id);
            int res2 = iActivityDao.deleteResource(id, -1);
            if (res1 < 0 || res2 < 0)
                throw new UserException("删除活动失败！");
            return true;
        } catch (Exception e) {
            throw new UserException("删除活动失败！");
        }
    }
    @Override
    @Transactional
    public Boolean regitemdelete(long id,String username){
        int res = iActivityDao.regitemDelete(id,username);
        if (res > 0 ){
            return true;
        }else {
            LOGGER.error("报名项删除失败，报名项id为："+id);
            throw new UserException("删除报名项失败！");
        }
    }
    /*签到管理*/

    @Override
    public List<SignListModel> signList(int curPage, int pageSum, long actId, String code, int sign) {
        try {
            List<SignListModel> res = iActivityDao.signList(actId, code, sign, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            throw new UserException("获取签到列表失败！");
        }
    }

    @Override
    public int signListSum(long actId, String code, int sign) {
        try {
            return iActivityDao.signListSum(actId, code, sign);
        } catch (Exception e) {
            throw new UserException("获取签到列表数量失败!");
        }
    }

    @Override
    public Boolean doSign(long actId, String openId) {
        if (actId <= 0 || StringUtils.isEmpty(openId))
            throw new UserException("参数不合法");
        int res = iActivityDao.doSign(actId, openId);
        if (res > 0)
            return true;
        else
            return false;
    }
    @Override
    public Boolean doReg(long actId, String openId) {
        if (actId <= 0 || StringUtils.isEmpty(openId))
            throw new UserException("参数不合法");
        int res = iActivityDao.doReg(actId, openId);
        if (res > 0)
            return true;
        else
            return false;
    }


}
