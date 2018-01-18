package com.binwang.controller;

import com.binwang.bean.analysis.Userprize;
import com.binwang.service.AnalysisService;
import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/analysis")
public class AnalysisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);
    @Resource
    private AnalysisService analysisService;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/user-prize", method = RequestMethod.GET)
    @ResponseBody
    public Object UserPrize(){
        try {
            List<Userprize> list = analysisService.listUserPrize();
            return ResponseUtil.okJSON(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.errorJSON(e.getMessage());
        }
    }
}
