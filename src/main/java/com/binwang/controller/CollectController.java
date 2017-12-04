package com.binwang.controller;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.service.CollectService;
import com.binwang.util.ResponseUtil;
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
 * Created by yy on 17/11/13.
 */
@Controller
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam("collectId") long collectId,
                       @RequestParam("type") int type,
                       @RequestParam("openId") String openId,
                       @RequestParam("curPage") int curPage,
                       @RequestParam("pageSum") int pageSum) {
        try {
            List<CListModel> list = collectService.getList(collectId, type,openId, curPage, pageSum);
            int sum = collectService.getListSum(collectId, type,openId);
            //获取已通过人数
            int approveSum = collectService.getApproveSum(collectId);
            Map<String, Object> m = new HashMap<>();
            m.put("list", list);
            m.put("sum", sum);
            m.put("approveSum", approveSum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取列表出错");
        }
    }


    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(@RequestParam("collectId") long collectId,
                         @RequestParam("itemId") long itemId) {
        try {
            CDetailModel res = collectService.getDetail(collectId, itemId);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取详情失败");
        }
    }

    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    @ResponseBody
    public Object handle(@RequestParam("collectId") long collectId,
                         @RequestParam("itemId") long itemId,
                         @RequestParam("approve") int approve) {
        try {
            Boolean res = collectService.handleApprove(collectId, itemId, approve);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("更新审核状态出错");
        }
    }
//投票设置
    @RequestMapping(value = "/post-vote", method = RequestMethod.POST)
    @ResponseBody
    public Object VoteParamPost(VoteParam voteParam) {
        try {
            Boolean res = collectService.addVoteParam(voteParam);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("出错,确保序号存在");
        }
    }

    @RequestMapping(value = "/post-first-img", method = RequestMethod.POST)
    @ResponseBody
    public Object firstImgPost(@RequestParam("id") long id,
                               @RequestParam("url") String url) {
        try {
            Boolean res = collectService.firstImgUpdate(id, url);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错,确保序号存在");
        }
    }

    @RequestMapping(value = "/post-detail-img",method = RequestMethod.POST)
    @ResponseBody
    public Object detailImgPost(@RequestParam("id") long id,
                                @RequestParam("urls") String urls) {
        try {
            Boolean res = collectService.detailImgUpdate(id, urls);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错,确保序号存在");
        }
    }
}
