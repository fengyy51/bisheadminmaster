package com.binwang.controller;

import com.binwang.service.PrizeService;
import com.binwang.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yy on 17/8/17.
 */
@Controller
@RequestMapping("/prize")
public class PrizeController {
    @Resource
    private PrizeService prizeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam("type") String type,
                       @RequestParam("relationId") int relationId,
                       @RequestParam("curPage") int curPage,
                       @RequestParam("pageSum") int pageSum) {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("list", prizeService.getList(type,relationId, curPage, pageSum));
            int sum = prizeService.getListSum(type,relationId);
            m.put("sum", sum);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object Search() {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("relationId", prizeService.getRelationId());
            m.put("type", prizeService.getType());
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }

    @RequestMapping(value = "/change-num", method = RequestMethod.POST)
    @ResponseBody
    public Object changeNum(@RequestParam("id") int id,
                            @RequestParam("num") int num,
                            @RequestParam("relationId") int relationId,
                            @RequestParam("name")String name,
                            @RequestParam("type")String type,
                            @RequestParam("ratio")int ratio,
                            @RequestParam("info")String info,
                            @RequestParam("duijiangTime")String duijiangTime,
                            @RequestParam("duijiangLoc")String duijiangLoc) {
        try {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", prizeService.changeNum(relationId, num, id,name,type,ratio,info,duijiangTime,duijiangLoc));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }

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
}
