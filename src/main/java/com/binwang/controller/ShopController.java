package com.binwang.controller;

import com.binwang.bean.shop.ShopListModel;
import com.binwang.bean.shop.ShopParam;
import com.binwang.service.ShopService;
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
 * Created by owen on 17/6/7.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    @Resource
    private ShopService shopService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ShopParam shopParam) {
        try {
            Boolean res = shopService.add(shopParam);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error("店铺添加接口：" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "curPage") int curPage,
                       @RequestParam(value = "pageSum") int pageSum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       @RequestParam(value = "area", required = false, defaultValue = "-1") int area,
                       @RequestParam(value = "floor", required = false, defaultValue = "-1") int  floor){
        try {
            List<ShopListModel> res = shopService.list(curPage, pageSum, name, area, floor);
            int sum = shopService.listSum(name, area, floor);
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
            ShopParam res = shopService.get(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Object edit(ShopParam shopParam){
        try {
            Boolean r = shopService.edit(shopParam);
            return ResponseUtil.okJSON(r);
        } catch (Exception e) {
            LOGGER.error("店铺编辑接口出错:" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("id") long id){
        try {
            Boolean r = shopService.delete(id);
            return ResponseUtil.okJSON(r);
        } catch (Exception e) {
            LOGGER.error("店铺删除接口出错:" + e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
}
