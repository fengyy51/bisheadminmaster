package com.binwang;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication

//@EnableScheduling//执行定时任务  task文件夹中
public class FdBinwangApplication {

	public static void main(String[] args) {

		SpringApplication.run(FdBinwangApplication.class, args);
	}
}
