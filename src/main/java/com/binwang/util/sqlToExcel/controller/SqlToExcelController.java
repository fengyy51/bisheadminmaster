package com.binwang.util.sqlToExcel.controller;

import com.binwang.util.sqlToExcel.service.VoteRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by owen on 17/8/23.
 */
@Controller
@RequestMapping("/sql-to-excel")
public class SqlToExcelController {
    @Resource
    private VoteRecordService voteRecordService;

    @RequestMapping("/vote-record")
    @ResponseBody
    public Object vote(){
        return voteRecordService.voteRecord();
    }


}
