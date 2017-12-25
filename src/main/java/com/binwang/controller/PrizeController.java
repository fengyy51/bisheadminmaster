package com.binwang.controller;

import com.binwang.bean.prize.PrizeListModel;
import com.binwang.bean.prize.PrizeParam;
import com.binwang.bean.prize.PrizeUserModel;
import com.binwang.service.PrizeService;
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
 * Created by yy on 17/8/17.
 */
@Controller
@RequestMapping("/prize")
public class PrizeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeController.class);
    @Resource
    private PrizeService prizeService;
//奖品列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam("name") String name,
                       @RequestParam("type") String type,
                       @RequestParam("actName") String actName,
                       @RequestParam("curPage") int curPage,
                       @RequestParam("pageSum") int pageSum) {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("list", prizeService.getList(name,type,actName, curPage, pageSum));
            int sum = prizeService.getListSum(name,type,actName);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
    //抽奖奖项，关联活动获取
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object Search() {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("actName", prizeService.getActName());
            m.put("type", prizeService.getType());
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
    //抽奖奖品数据修改
    @RequestMapping(value = "/change-num", method = RequestMethod.POST)
    @ResponseBody
    public Object changeNum(@RequestParam("id") int id,
                            @RequestParam("num") int num,
                            @RequestParam("actName") String actName,
                            @RequestParam("name")String name,
                            @RequestParam("type")String type,
                            @RequestParam("ratio")int ratio,
                            @RequestParam("info")String info,
                            @RequestParam("duijiangTime")String duijiangTime,
                            @RequestParam("duijiangLoc")String duijiangLoc) {
        try {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", prizeService.changeNum(actName, num, id,name,type,ratio,info,duijiangTime,duijiangLoc));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
//抽奖奖品数据删除
    @RequestMapping(value = "/param/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object ParamDelete(@RequestParam("id") int id) {
        try {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", prizeService.paramDelete(id));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
    //抽奖设置发起
    @RequestMapping(value = "/post-prize-param", method = RequestMethod.POST)
    @ResponseBody
    public Object PrizeParamPost(PrizeParam prizeParam) {
        try {
            Boolean res = prizeService.addPrizeParam(prizeParam);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("增加抽奖设置出错");
        }
    }
    //抽奖设置获得
    @RequestMapping(value = "/get-prize-param", method = RequestMethod.GET)
    @ResponseBody
    public Object PrizeParamGet(@RequestParam("id")int id) {
        try {
            PrizeParam res = prizeService.getPrizeParam(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("获取抽奖设置出错");
        }
    }
    //抽奖设置编辑
    @RequestMapping(value = "/edit-prize-param", method = RequestMethod.POST)
    @ResponseBody
    public Object PrizeParamEdit( PrizeParam prizeParam) {
        try {
            Boolean res = prizeService.editPrizeParam(prizeParam);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseUtil.errorJSON("编辑抽奖设置出错");
        }
    }
    //抽奖设置删除
    @RequestMapping(value = "/delete-prize-param", method = RequestMethod.POST)
    @ResponseBody
    public Object deletePrizeParam(@RequestParam(value = "id") int id) {
        try {
            Boolean res = prizeService.deletePrizeParam(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    //抽奖列表
    @RequestMapping(value = "/list-prize", method = RequestMethod.GET)
    @ResponseBody
    public Object prizelist(
                       @RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       @RequestParam(value="username") String username,
                       @RequestParam(value = "begin", required = false, defaultValue = "") String begin,
                       @RequestParam(value = "end", required = false, defaultValue = "") String end) {
        try {
            List<PrizeListModel> res = prizeService.list(curPage, pageSum, name,username, begin, end);
            int sum = prizeService.listSum(name,username, begin, end);
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
    //抽奖用户列表获取
    @RequestMapping(value = "/list-user", method = RequestMethod.GET)
    @ResponseBody
    public Object PrizeUserlist(
            @RequestParam(value = "curPage") int curPage,
            @RequestParam(value = "pageSum") int pageSum,
            @RequestParam(value = "actId") long actId,
            @RequestParam(value = "isUse") int isUse,
            @RequestParam(value = "code", required = false, defaultValue = "") String code) {
        try {
//            System.out.println("actID"+actId);
            List<PrizeUserModel> res = prizeService.userList(curPage, pageSum, actId, code, isUse);
            int sum = prizeService.userListSum(actId, code, isUse);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

}
