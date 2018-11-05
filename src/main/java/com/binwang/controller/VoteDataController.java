package com.binwang.controller;


import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;
import com.binwang.service.VoteDataService;
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

/**
 * Created by yy on 18/5/10.
 */
@Controller
@RequestMapping("/vote-data")
public class VoteDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteDataController.class);

    @Resource
    private VoteDataService voteDataService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
                      @RequestParam(value = "username")String username,
                      @RequestParam(value = "actId")long actId,
                      @RequestParam(value = "content")String content,
                      @RequestParam(value = "voteDataImgs")List<String> voteDataImgs) {
        try {
            Boolean res = voteDataService.add(username,actId,content,voteDataImgs);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error("投票作品信息添加接口：" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list( @RequestParam(value = "username")String username,
                        @RequestParam(value = "content") String content,
                       @RequestParam(value = "actId") String actId,
                       @RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum){
        try {
            List<VoteListModel> res = voteDataService.list(username,content,actId,curPage, pageSum);
            int sum = voteDataService.listSum(username,content,actId);
            HashMap<String,Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public Object get(@RequestParam("id") long id){
        try {
            VoteParam res = voteDataService.get(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Object edit(VoteParam voteParam){
        try {
            Boolean r = voteDataService.edit(voteParam);
            return ResponseUtil.okJSON(r);
        } catch (Exception e) {
            LOGGER.error("投票作品信息编辑接口出错:" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("id") long id){
        try {
            Boolean r = voteDataService.delete(id);
            return ResponseUtil.okJSON(r);
        } catch (Exception e) {
            LOGGER.error("投票作品信息删除接口出错:" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
}
