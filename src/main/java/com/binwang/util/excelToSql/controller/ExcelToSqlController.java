package com.binwang.util.excelToSql.controller;

import com.binwang.util.excelToSql.service.HandlePrize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by owen on 17/8/7.
 */
@Controller
public class ExcelToSqlController {

    @Resource
    private HandlePrize handlePrize;

    @RequestMapping(value = "/manual/excel/prize", method = RequestMethod.GET)
    @ResponseBody
    public Object doExcel() {
        Boolean res = handlePrize.doService();
        return res;
    }

    @RequestMapping(value = "/manual/excel/collect_info", method = RequestMethod.GET)
    @ResponseBody
    public Object doCollectInfo() {
        return handlePrize.doCollectInfo();
    }

}
