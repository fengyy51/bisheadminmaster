package com.binwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling//执行定时任务  task文件夹中
public class FdBinwangApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdBinwangApplication.class, args);
	}
}
