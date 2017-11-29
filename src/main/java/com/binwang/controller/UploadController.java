package com.binwang.controller;

import com.binwang.bean.config.OssBean;
import com.binwang.util.OssUtil;
import com.binwang.util.ResponseUtil;
import com.binwang.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by owen on 17/5/8.
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);


    @Resource(name = "ossBean")
    private OssBean ossBean;


    private final String prePath = "img/";

    @RequestMapping(value = "/upload/img", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImg(@RequestParam(value = "file") MultipartFile file) {
        try {
            String name = URLDecoder.decode(file.getOriginalFilename().replace("-",""), "UTF-8");
            String filePath = prePath + UUIDUtil.getUUID() + "---" + name;
            OssUtil.objectUp(ossBean, file, filePath);
            return ResponseUtil.okJSON(ossBean.getBaseUrl() + filePath);
        } catch (IOException e) {
            LOGGER.error("上传文件接口失败");
            return ResponseUtil.errorJSON("上传文件接口失败！");
        }
    }

    @RequestMapping(value = "/delete/img", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteImg(@RequestParam(value = "path") String path) {
        try {
            String name = path.replace(ossBean.getBaseUrl(),"");
            OssUtil.deleteObject(ossBean,name);
            return ResponseUtil.okJSON(true);
        } catch (Exception e) {
            LOGGER.error("删除文件失败！");
            return ResponseUtil.errorJSON("删除文件失败！");
        }
    }

}
