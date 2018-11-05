package com.binwang.controller;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.bean.gallery.GalleryProModel;
import com.binwang.service.ActivityService;
import com.binwang.service.GalleryService;
import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by yy 2018-3-7
 */
@Controller
@RequestMapping(value = "/gallery")
public class GalleryController

{
        private static final Logger LOGGER = LoggerFactory.getLogger(com.binwang.controller.GalleryController.class);

        @Resource
        private GalleryService galleryService;

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        @ResponseBody
        public Object list(@RequestParam(value = "curPage") int curPage,
                           @RequestParam(value = "pageSum") int pageSum,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value="username") String username,
                           @RequestParam(value = "begin", required = false, defaultValue = "") String begin,
                           @RequestParam(value = "end", required = false, defaultValue = "") String end) {
            try {
                List<GalleryListModel> res =new ArrayList<>();
                int sum=0;
//                List<String>admins=galleryService.getListAdmin();
//                if(username.equals("binwang158")){
                    LOGGER.info(username);
                    res=galleryService.list(username,curPage, pageSum, name,begin, end);
                    LOGGER.info(res.toString());
                    sum = galleryService.listSum(username,name, begin, end);
//                }
                HashMap<String, Object> m = new HashMap<>();
                m.put("list", res);
                m.put("sum", sum);
                return ResponseUtil.okJSON(m);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return ResponseUtil.errorJSON(e.getMessage());
            }
        }
    @RequestMapping("/list-production")
    @ResponseBody
    public Object list(@RequestParam("name") String name,
                       @RequestParam("type") int type,
                       @RequestParam("galleryId") String galleryId,
                       @RequestParam("curPage") int curPage,
                       @RequestParam("pageSum") int pageSum) {
        try {
            List<GalleryProModel> list = galleryService.getListPro(name, type,galleryId, curPage, pageSum);
            int sum = galleryService.getListProSum(name, type,galleryId);
            //获取已通过人数
            int approveSum = galleryService.getApproveSum(galleryId);
            Map<String, Object> m = new HashMap<>();
            m.put("list", list);
            m.put("sum", sum);
            m.put("approveSum", approveSum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取专题作品列表出错");
        }
    }
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    @ResponseBody
    public Object handle(@RequestParam("galleryId") String galleryId,
                         @RequestParam("productionId") String productionId,
                         @RequestParam("type") int type) {
        try {
            Boolean res = galleryService.handleApprove(galleryId, productionId, type);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("更新审核状态出错");
        }
    }
    //增加活动管理员
    @RequestMapping(value = "/add-admin", method = RequestMethod.POST)
    @ResponseBody
    public Object addAdmin(@RequestParam("galleryName") String galleryName,
                         @RequestParam("galleryAdmin") String galleryAdmin ) {
        try {
            Boolean res = galleryService.addAdmin(galleryName, galleryAdmin);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("增加活动管理员出错");
        }
    }
    @RequestMapping(value = "/allpass", method = RequestMethod.POST)
    @ResponseBody
    public Object allPass(@RequestParam("galleryId") String galleryId) {
        try {
            Boolean res = galleryService.allPass(galleryId);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("全部审核通过出错");
        }
    }
    @RequestMapping(value = "/allreject", method = RequestMethod.POST)
    @ResponseBody
    public Object allReject(@RequestParam("galleryId") String galleryId) {
        try {
            Boolean res = galleryService.allReject(galleryId);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("全部取消审核出错");
        }
    }


}
