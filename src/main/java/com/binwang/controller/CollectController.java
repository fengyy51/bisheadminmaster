package com.binwang.controller;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.collect.VoteListModel;
import com.binwang.bean.collect.VoteResultModel;
import com.binwang.service.CollectService;
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
 * Created by yy on 17/11/13.
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectController.class);
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
    //投票列表
    @RequestMapping(value = "/list-vote", method = RequestMethod.GET)
    @ResponseBody
    public Object votelist(
            @RequestParam(value = "curPage") int curPage,
            @RequestParam(value = "pageSum") int pageSum,
            @RequestParam(value = "actName", required = false, defaultValue = "") String actName,
            @RequestParam(value="username") String username,
            @RequestParam(value = "begin", required = false, defaultValue = "") String begin,
            @RequestParam(value = "end", required = false, defaultValue = "") String end) {
        try {
            List<VoteListModel> res = collectService.listVote(curPage, pageSum, actName,username, begin, end);
            int sum = collectService.listVoteSum(actName,username, begin, end);
            System.out.println(username);
            System.out.println(sum);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    //投票设置发起
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
    //投票设置获取
    @RequestMapping(value = "/vote-param-get", method = RequestMethod.GET)
    @ResponseBody
    public Object voteParamGet(@RequestParam(value = "id") long id  ) {
        try {
            VoteParam res = collectService.VoteParamGet(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    //投票设置编辑
    @RequestMapping(value = "/vote-param-edit", method = RequestMethod.POST)
    @ResponseBody
    public Object VoteParamEdit(VoteParam voteParam) {
        try {
            Boolean res = collectService.VoteParamEdit(voteParam);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("修改投票设置出错");
        }
    }
    //投票设置删除
    @RequestMapping(value = "/vote-param-delete", method = RequestMethod.POST)
    @ResponseBody
    public Object PrizeParamDelete(@RequestParam(value = "id") int id) {
        try {
            Boolean res = collectService.PrizeParamDelete(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    //投票结果获取
    @RequestMapping(value = "/vote-result", method = RequestMethod.GET)
    @ResponseBody
    public Object VoteResult(
            @RequestParam(value = "curPage") int curPage,
            @RequestParam(value = "pageSum") int pageSum,
            @RequestParam(value = "itemId") long itemId,
            @RequestParam(value = "actId") long actId) {
        try {
            List<VoteResultModel> res = collectService.voteResult(curPage, pageSum,itemId,actId);
            int sum = collectService.voteResultSum(itemId,actId);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    //投票结果编辑
    @RequestMapping(value = "/vote-result-edit", method = RequestMethod.POST)
    @ResponseBody
    public Object VoteResultEdit(VoteResultModel voteResultModel) {
        try {
            Boolean res = collectService.VoteResultEdit(voteResultModel);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("修改投票结果出错");
        }
    }
    //刷票行为分析开始

    //获取投票活动名称
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object Search(@RequestParam("username")String username) {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("actName", collectService.getActName(username));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
    //获取异常数据列表
    @RequestMapping(value = "/brush-list", method = RequestMethod.GET)
    @ResponseBody
    public Object Brushlist(@RequestParam("actName")String actName,
                            @RequestParam("interval")String interval,
                            @RequestParam("num")String num,
                            @RequestParam("curPage")int curPage,
                            @RequestParam("pageSum")int pageSum) {
        try {
            Map<String, Object> m = new HashMap<>();
            List<>list=collectService.getBrushlist(actName,interval,num,curPage,pageSum));
            m.put("list",list);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取异常数据列表出错");
        }
    }


}
