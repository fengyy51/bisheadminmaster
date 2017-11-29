package com.binwang.config;

import com.binwang.bean.config.OssBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by owen on 17/5/9.
 */
@Configuration
public class OssConfig {
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.endPoint}")
    private String endPoint;
    @Value("${oss.baseUrl}")
    private String baseUrl;
    @Value("${oss.bucketName}")
    private String bucketName;

    @Bean(name = "ossBean")
    public OssBean getOss() {
        return new OssBean(accessKeyId, accessKeySecret, endPoint, baseUrl, bucketName);
    }
}
