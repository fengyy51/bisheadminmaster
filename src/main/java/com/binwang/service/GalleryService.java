package com.binwang.service;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.bean.gallery.GalleryProModel;

import java.util.List;
/**
 * Created by yy on 17/12/13.
 */
public interface GalleryService {
    List<GalleryListModel> list(String username,int curPage, int pageSum, String name, String begin, String end);
    int listSum(String username,String name,String begin, String end);
    List<GalleryProModel> getListPro(String name, int type, String galleryId, int curPage, int pageSum);
    int getListProSum( String name,int type,String galleryId);

    int getApproveSum(String galleryId);

    Boolean handleApprove(String galleryId,String productionId, int type);
    Boolean addAdmin(String galleryName,String galleryAdmin);
    Boolean allPass(String galleryId);
    Boolean allReject(String galleryId);
}
