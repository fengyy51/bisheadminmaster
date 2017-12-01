package com.binwang.service.impl;

import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.dao.ICollectDao;
import com.binwang.exception.UserException;
import com.binwang.service.CollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by owen on 17/7/13.
 */
@Service
public class CollectServiceImpl implements CollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectServiceImpl.class);

    @Resource
    private ICollectDao collectDao;

    @Override
    public List<CListModel> getList(long collectId, int type, String openId,int curPage, int pageSum) {
        try {
            List<CListModel> res = collectDao.getList(collectId, type,openId, (curPage - 1) * pageSum, pageSum);
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
    public int getListSum(long collectId, int type,String openId) {
        try {
            return collectDao.listSum(collectId, type,openId);
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
}
