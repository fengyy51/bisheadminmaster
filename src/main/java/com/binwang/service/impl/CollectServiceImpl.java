package com.binwang.service.impl;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.*;
import com.binwang.dao.ICollectDao;
import com.binwang.exception.UserException;
import com.binwang.service.CollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 17/7/13.
 */
@Service
public class CollectServiceImpl implements CollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectServiceImpl.class);

    @Resource
    private ICollectDao collectDao;

    @Override
    public List<CListModel> getList(long collectId, int type, String openId, int curPage, int pageSum) {
        try {
            List<CListModel> res = collectDao.getList(collectId, type, openId, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error("获取作品集列表失败，collectId为:" + collectId);
            throw new RuntimeException("获取作品集列表失败");
        }
    }

    @Override
    public CDetailModel getDetail(long collectId, long itemId) {
        if (collectId < 0 || itemId < 0)
            throw new UserException("参数不合法");
        return collectDao.getDetail(itemId, collectId);
    }

    @Override
    @Transactional
    public Boolean handleApprove(long collectId, long itemId, int type) {
        if (collectId < 0 || itemId < 0)
            throw new UserException("参数不合法");
        int res = collectDao.handleApprove(collectId, itemId, type);
        if (res > 0)
            return true;
        else {
            LOGGER.error("更新作品是否审核失败，id为：" + itemId);
            return false;
        }
    }

    @Override
    public int getListSum(long collectId, int type, String openId) {
        try {
            return collectDao.listSum(collectId, type, openId);
        } catch (Exception e) {
            LOGGER.error("获取列表数量出错，collectId为：" + collectId);
            throw new RuntimeException("获取列表数量出错");
        }
    }

    @Override
    public int getApproveSum(long collectId) {
        try {
            return collectDao.approveSum(collectId);
        } catch (Exception e) {
            LOGGER.error("获取审核数量失败，collectId为：" + collectId);
            throw new RuntimeException("获取审核数量失败");
        }
    }

    @Override
    @Transactional
    public Boolean firstImgUpdate(long id, String url) {
        if (id <= 0)
            throw new RuntimeException("参数不合法");
        try {
            int res = collectDao.firstImgUpdate(id, url);
            if (res > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new RuntimeException("出错");
        }
    }

    @Override
    @Transactional
    public Boolean detailImgUpdate(long id, String urls) {
        if (id <= 0 || StringUtils.isEmpty(urls))
            throw new RuntimeException("参数不合法");
        try {
            int res = collectDao.detailImgUpdate(id, urls);
            if (res > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new RuntimeException("出错");
        }
    }

    @Override
    @Transactional
    public Boolean addVoteParam(VoteParam voteParam) throws RuntimeException {
        if (collectDao.addVoteParam(voteParam) > 0) {
            return true;
        } else {
            throw new RuntimeException("插入投票设置失败，actid为" + voteParam.getActId());
        }
    }
    @Override
    @Transactional
    public List<VoteListModel>listVote(int curPage, int pageSum, String actName, String username, String begin, String end){
        try {
            List<VoteListModel> res = collectDao.listVote(actName, username,begin, end, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            System.out.println(e);
            throw new UserException("获取投票活动列表失败！");
        }
    }

    @Override
    @Transactional
    public int listVoteSum(String actName,String username, String begin, String end) {
        try {
            return collectDao.listVoteSum(actName,username ,begin, end);
        } catch (Exception e) {
            throw new UserException("获取投票活动列表数量失败!");
        }
    }
    @Override
    @Transactional
    public VoteParam VoteParamGet(long id){
        try {
            return collectDao.VoteParamGet(id);
        } catch (Exception e) {
            throw new UserException("获取投票活动设置失败!");
        }
    }
    @Override
    @Transactional
    public  Boolean VoteParamEdit(VoteParam voteParam){
        if (collectDao.updateVoteParam(voteParam) > 0) {
            return true;
        } else {
            throw new RuntimeException("更新投票设置失败，actid为" + voteParam.getActId());
        }
    }
    @Override
    @Transactional
    public Boolean PrizeParamDelete(int id){
        if(collectDao.PrizeParamDelete(id)>0){
            return true;
        }else{
            return false;
        }
    }
    //投票结果获取
    @Override
    @Transactional
    public List<VoteResultModel> voteResult(int curPage, int pageSum, long itemId,long actId){
        try {
            List<VoteResultModel> res = collectDao.voteResult(itemId,actId, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            System.out.println(e);
            throw new UserException("获取投票结果失败！");
        }
    }
    @Override
    @Transactional
    public int voteResultSum(long itemId,long actId){
        try {
            return collectDao.voteResultSum(itemId,actId);
        } catch (Exception e) {
            System.out.println(e);
            throw new UserException("获取投票活动列表数量失败!");
        }
    }
    //投票结果数编辑
    @Override
    @Transactional
    public Boolean VoteResultEdit(VoteResultModel voteResultModel){
        if (collectDao.voteResultEdit(voteResultModel) > 0) {
            return true;
        } else {
            throw new RuntimeException("更新投票结果数失败，actid为" + voteResultModel.getId());
        }
    }
    @Override
    @Transactional
    public List<String> getActName(String username){
        try {
            return collectDao.getActName(username);
        }catch (Exception e){
            System.out.println(e);
            throw new UserException("获取投票活动名称失败");
        }
    }
    //刷票记录查询
    @Override
    @Transactional
    public List<VoteBrushModel> getBrushlist(int id, String actName, String begin, String end, int num){
        try{
            return collectDao.getBrushlist(id,actName,begin,end,num);
        }catch (Exception e){
            System.out.println(e);
            throw new UserException("获取刷票记录失败");
        }
    }
    @Override
    @Transactional
    public int getBrushlistSum(int id,String actName,String begin,String end,int num){
        try{
            return collectDao.getBrushlistSum(id,actName,begin,end,num);
        }catch (Exception e){
            System.out.println(e);
            throw new UserException("获取刷票记录数失败");
        }
    }
    @Override
    @Transactional
    public List<Integer> getRecordIDS(String actName) {
        try{
            return collectDao.getRecordIDS(actName);
        }catch (Exception e){
            System.out.println(e);
            throw new UserException("获取id数失败");
        }
    }
    @Override
    @Transactional
    public int getVoteId(String actName){
        try{
            return collectDao.getVoteId(actName);
        }catch (Exception e){
            System.out.println(e);
            throw new UserException("获取投票活动id失败");
        }
    }
}
