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
    public Object list(@RequestParam("type") int type,
                       @RequestParam("relationId") int relationId) {
        try {
            Map<String, Object> m = new HashMap<>();
            if (type == 0)
                m.put("list", prizeService.getOneAndTwo(relationId));
            else if (type == 1)
                m.put("list", prizeService.getThreeAndFour(relationId));
            else
                m.put("list", prizeService.getNoLimit(relationId));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }

    @RequestMapping(value = "/change-num", method = RequestMethod.POST)
    @ResponseBody
    public Object changeSum(@RequestParam("id") int id,
                            @RequestParam("num") int num,
                            @RequestParam("relationId") int relationId) {
        try {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", prizeService.changeNoLimitNum(relationId, num, id));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错");
        }
    }
}
