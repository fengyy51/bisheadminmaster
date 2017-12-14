package com.binwang.util.excelToSql.controller;

import com.binwang.util.excelToSql.service.HandlePrize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by yy on 17/8/7.
 */
@Controller
@RequestMapping(value = "/manual")
public class ExcelToSqlController {

    @Resource
    private HandlePrize handlePrize;

    @RequestMapping(value = "/excel/prize", method = RequestMethod.POST)
    @ResponseBody
    public Object doExcel(@Param("fileData")MultipartFile file) {
        Boolean res = handlePrize.doService(file);
        return res;
    }

    @RequestMapping(value = "/excel/collect_info", method = RequestMethod.GET)
    @ResponseBody
    public Object doCollectInfo() {
        return handlePrize.doCollectInfo();
    }

}
