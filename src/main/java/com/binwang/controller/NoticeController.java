package com.binwang.controller;

import com.binwang.bean.notice.NoticeDO;
import com.binwang.service.NoticeService;
import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yy on 17/5/3.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Resource
    private NoticeService noticeService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam(value = "content") String content,
                      @RequestParam(value = "pubDate") String pubDate,
                      @RequestParam(value = "username")String username) {
        Boolean res = noticeService.add(content, pubDate,username);
        if (res)
            return ResponseUtil.okJSON(res);
        else {
            LOGGER.error("/notice/add接口添加失败！");
            return ResponseUtil.errorJSON("添加失败！");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum,
                       @RequestParam(value = "username")String username) {
        List<NoticeDO> res = noticeService.list(curPage, pageSum,username);
        int sum = noticeService.numOfNotice(username);
        if (res != null && sum != -1) {
            Map<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } else {
            if (res == null)
                LOGGER.error("/notice/list接口获取失败！");
            if (sum == -1)
                LOGGER.error("/notice/list列表数量失败！");
            return ResponseUtil.errorJSON("获取列表失败！");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object detele(@RequestParam(value = "id") Long id) {
        Boolean res = noticeService.delete(id);
        if (res)
            return ResponseUtil.okJSON(true);
        else {
            LOGGER.error("/notice/delete删除公告接口出错！");
            return ResponseUtil.errorJSON("/notice/delete删除公告接口出错！");
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Object edit(NoticeDO noticeDO){
        Boolean res = noticeService.update(noticeDO);
        if (res)
            return ResponseUtil.okJSON(true);
        else {
            LOGGER.error("/notice/edit删除公告接口出错！");
            return ResponseUtil.errorJSON("/notice/edit删除公告接口出错！");
        }
    }
}