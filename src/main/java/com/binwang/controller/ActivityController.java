package com.binwang.controller;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;
import com.binwang.service.ActivityService;
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
 * Created by yy on 17/5/11.
 */
@Controller
@RequestMapping(value = "/act")
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ActParam actParam) {
        try {
            Boolean res = activityService.add(actParam);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    @RequestMapping(value = "/reg_item_add", method = RequestMethod.POST)
    @ResponseBody
    public Object regadd(RegItemListModel regItemListModel) {
        try {
            Boolean res = activityService.regitemadd(regItemListModel);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(ActParam actParam) {
        try {
            Boolean res = activityService.edit(actParam);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/reg_item_edit", method = RequestMethod.POST)
    @ResponseBody
    public Object regedit(RegItemListModel regItemListModel) {
        try {
            Boolean res = activityService.regitemedit(regItemListModel);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam(value = "id") long id) {
        try {
            Boolean res = activityService.delete(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/reg_item_delete", method = RequestMethod.POST)
    @ResponseBody
    public Object regdelete(@RequestParam(value = "id") long id,@RequestParam(value = "username")String username) {
        try {
            Boolean res = activityService.regitemdelete(id,username);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Object get(@RequestParam(value = "id") long id) {
        try {
            ActParam actParam = activityService.get(id);
            return ResponseUtil.okJSON(actParam);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value="username") String username,
                       @RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       @RequestParam(value = "begin", required = false, defaultValue = "") String begin,
                       @RequestParam(value = "end", required = false, defaultValue = "") String end) {
        try {
            List<ActListModel> res = activityService.list(curPage, pageSum, name,username, begin, end);
            int sum = activityService.listSum(username,name, begin, end);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/reg_item_list", method = RequestMethod.GET)
    @ResponseBody
    public Object reglist(@RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum,
                       @RequestParam(value = "username")String username) {
        try {
            List<RegItemListModel> res = activityService.regitemlist(curPage, pageSum,username);
            int sum = activityService.reglistSum(username);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
//            LOGGER.info(new Integer(sum).toString());
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
    @RequestMapping(value = "/act_reg_list", method = RequestMethod.GET)
    @ResponseBody
    public Object actreglist(@RequestParam(value = "username")String username) {
        try {
            List<RegItemListModel> res = activityService.actregitemlist(username);
            int sum = activityService.reglistSum(username);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
//报名签到列表包括搜索 验证码、已签到、已报名、未支付、未签到
    @RequestMapping(value = "/sign/list")
    @ResponseBody
    public Object signList(
            @RequestParam(value = "curPage") int curPage,
                           @RequestParam(value = "pageSum") int pageSum,
                           @RequestParam(value = "actId") long actId,
                           @RequestParam(value = "sign") int sign,
                           @RequestParam(value = "code", required = false, defaultValue = "") String code) {
        try {
            List<SignListModel> res = activityService.signList(curPage, pageSum, actId, code, sign);
            int sum = activityService.signListSum(actId, code, sign);
            HashMap<String, Object> m = new HashMap<>();
            m.put("list", res);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/do-sign", method = RequestMethod.POST)
    @ResponseBody
    public Object doSign(@RequestParam(value = "openId") String openId,
                         @RequestParam(value = "actId") long actId) {
        try {
            Boolean res = activityService.doSign(actId, openId);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("签到失败");
        }
    }
//    支付成功后修改报名状态。
    @RequestMapping(value = "/do-reg", method = RequestMethod.POST)
    @ResponseBody
    public Object doReg(@RequestParam(value = "openId") String openId,
                         @RequestParam(value = "actId") long actId) {
        try {
            Boolean res = activityService.doReg(actId, openId);
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", res);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON("签到失败");
        }
    }
}
