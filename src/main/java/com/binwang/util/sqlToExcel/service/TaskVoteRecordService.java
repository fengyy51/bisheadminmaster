package com.binwang.util.sqlToExcel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by owen on 17/8/24.
 */
@Service
public class TaskVoteRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskVoteRecordService.class);

    @Autowired
    private JavaMailSender mailSender;

    private final String centOSExcelPath = "/root/excel/";

    private final String voteExcelPath = "/Users/owen/Desktop/vote.xls";

    @Resource
    private VoteRecordService voteRecordService;


    @Scheduled(cron = "0 0/30 17-22 24 8 ?")
    public void sendMail() throws Exception {
        long end = System.currentTimeMillis();
        long start = end - 30 * 60 * 1000;
        String name = "投票监控汇总" + com.binwang.util.DateUtil.timeStamp2Date(start, "HH:mm") + "-" + com.binwang.util.DateUtil.timeStamp2Date(end, "HH:mm") + ".xls";
        if (!voteRecordService.voteRecord(start, end, name)) {
            LOGGER.error("生成excel出错：" + name);
            return;
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("375294890@qq.com");
        helper.addCc("liuhongnan53@163.com");
        helper.setTo("348614081@qq.com");
        helper.setSubject(name);
        helper.setText("见附件");
        FileSystemResource file = new FileSystemResource(new File(centOSExcelPath + name));
//        FileSystemResource file = new FileSystemResource(new File(voteExcelPath));
        helper.addAttachment(name, file);
        mailSender.send(mimeMessage);
    }

    @Scheduled(cron = "0 0/5 23 24 8 ?")
    public void sendMailTwo() throws Exception {
        long end = System.currentTimeMillis();
        long start = end - 5 * 60 * 1000;
        String name = "投票监控汇总" + com.binwang.util.DateUtil.timeStamp2Date(start, "HH:mm") + "-" + com.binwang.util.DateUtil.timeStamp2Date(end, "HH:mm") + ".xls";
        if (!voteRecordService.voteRecord(start, end, name)) {
            LOGGER.error("生成excel出错：" + name);
            return;
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("375294890@qq.com");
        helper.addCc("liuhongnan53@163.com");
        helper.setTo("348614081@qq.com");
        helper.setSubject(name);
        helper.setText("见附件");
        FileSystemResource file = new FileSystemResource(new File(centOSExcelPath + name));
//        FileSystemResource file = new FileSystemResource(new File(voteExcelPath));
        helper.addAttachment(name, file);
        mailSender.send(mimeMessage);
    }
}
