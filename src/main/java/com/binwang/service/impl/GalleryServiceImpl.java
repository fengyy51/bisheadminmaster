package com.binwang.service.impl;

import com.binwang.bean.activity.*;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.bean.gallery.GalleryProModel;
import com.binwang.dao.IActivityDao;
import com.binwang.dao.IGalleryDao;
import com.binwang.exception.UserException;
import com.binwang.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GalleryServiceImpl implements GalleryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryServiceImpl.class);


    @Resource
    private IGalleryDao iGalleryDao;

    @Override
    public List<GalleryListModel> list(String username,int curPage, int pageSum, String name, String begin, String end) {
        try {
            List<GalleryListModel> res = iGalleryDao.listGallery(username,name,begin, end, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UserException("获取活动列表失败！");
        }
    }
    @Override
    public int listSum(String username,String name, String begin, String end) {
        try {
            return iGalleryDao.listGallerySum(username,name,begin, end);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UserException("获取活动列表数量失败!");
        }
    }
    @Override
    public List<GalleryProModel> getListPro(String name, int type, String galleryId, int curPage, int pageSum){
        try {
            List<GalleryProModel> res = iGalleryDao.getProList(galleryId, type, name, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("获取专题作品集列表失败，galleryId为:" + galleryId);
            throw new RuntimeException("获取专题作品集列表失败");
        }
    }
    @Override
    public int getListProSum(String name, int type,String galleryId) {
        try {
            return iGalleryDao.listProSum(name,type,galleryId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("获取专题列表数量出错，galleryId为：" + galleryId);
            throw new RuntimeException("获取列表数量出错");
        }
    }

    @Override
    public int getApproveSum(String galleryId) {
        try {
            return iGalleryDao.approveSum(galleryId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("获取审核数量失败，galleryId为：" + galleryId);
            throw new RuntimeException("获取审核数量失败");
        }
    }

    @Override
    @Transactional
    public Boolean handleApprove(String galleryId,String productionId, int type) {
        int res = iGalleryDao.handleApprove(galleryId, productionId, type);
        if (res > 0)
            return true;
        else {
            LOGGER.error("更新作品是否审核失败，id为：" + productionId);
            return false;
        }
    }
    @Override
    @Transactional
    public Boolean addAdmin(String galleryName,String galleryAdmin){
        int res = iGalleryDao.addAdmin(galleryName, galleryAdmin);
        if (res > 0)
            return true;
        else {
            LOGGER.error("添加活动管理者失败，galleryName为：" + galleryName);
            return false;
        }
    }
    @Override
    @Transactional
    public Boolean allPass(String galleryId) {
        int res = iGalleryDao.allPass( galleryId);
        if (res > 0)
            return true;
        else {
            LOGGER.error("全部审核通过失败，galleryId为：" + galleryId);
            return false;
        }
    }
    @Override
    @Transactional
    public Boolean allReject(String galleryId) {
        int res = iGalleryDao.allReject( galleryId);
        if (res > 0)
            return true;
        else {
            LOGGER.error("全部取消审核通过失败，galleryId为：" + galleryId);
            return false;
        }
    }



}
