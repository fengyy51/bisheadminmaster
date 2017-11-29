package com.binwang.util.sqlToExcel.service;

import com.binwang.util.sqlToExcel.bean.VoteRecord;
import com.binwang.util.sqlToExcel.dao.IVoteRecordDao;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by owen on 17/8/23.
 */
@Service
public class VoteRecordService {
    private final String voteExcelPath = "/Users/owen/Desktop/vote.xls";

    private final String centOSExcelPath = "/root/excel/";

    private final int[] ids = {80, 82, 115, 117, 119, 123, 124, 125, 145, 150, 154, 156, 172, 173, 174, 177, 186, 187, 197, 199, 208,
            209, 213, 216, 217, 221, 223, 226, 229, 240, 243, 245, 251, 257, 260, 262, 265, 276, 280, 281, 283, 290, 302, 303,
            304, 305, 306, 307, 308, 309};

    private final int[] idss = {281,209,156,217,308,173,154};

    @Resource
    private IVoteRecordDao voteRecordDao;

    public Boolean voteRecord() {
        Workbook wb = new HSSFWorkbook();
        for (int i = 0; i < ids.length; i++) {
            int cur = ids[i];
            Sheet sheet = wb.createSheet("序号" + cur);
            sheet.setColumnWidth(0, 20 * 256);
            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(20);
            Cell cell_1 = headerRow.createCell(0);
            cell_1.setCellValue("时间间隔");
            Cell cell_2 = headerRow.createCell(1);
            cell_2.setCellValue("票数");
            Row row;
            Cell cell;
            int rownum = 1;
            //背景颜色
            HSSFCellStyle my_style = (HSSFCellStyle) wb.createCellStyle();
            my_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            my_style.setFillForegroundColor(new HSSFColor.CORAL().getIndex());
            List<VoteRecord> items = voteRecordDao.getRecord(cur);
            for (VoteRecord item : items) {
                row = sheet.createRow(rownum);
                cell = row.createCell(0);
                cell.setCellValue(item.getPeriod());
                if (item.getSum() >= 50)
                    cell.setCellStyle(my_style);
                cell = row.createCell(1);
                cell.setCellValue(item.getSum());
                if (item.getSum() >= 50)
                    cell.setCellStyle(my_style);
                rownum++;
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(voteExcelPath);
            wb.write(out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                out = null;
            }
        }
    }


    public Boolean voteRecord(long start, long end, String name) {
        Workbook wb = new HSSFWorkbook();
        for (int i = 0; i < ids.length; i++) {
            int cur = ids[i];
            Sheet sheet = wb.createSheet("序号" + cur);
            sheet.setColumnWidth(0, 20 * 256);
            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(20);
            Cell cell_1 = headerRow.createCell(0);
            cell_1.setCellValue("时间间隔");
            Cell cell_2 = headerRow.createCell(1);
            cell_2.setCellValue("票数");
            Row row;
            Cell cell;
            int rownum = 1;
            //背景颜色
            HSSFCellStyle my_style = (HSSFCellStyle) wb.createCellStyle();
            my_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            my_style.setFillForegroundColor(new HSSFColor.CORAL().getIndex());
            List<VoteRecord> items = voteRecordDao.getRecordTask(cur, start / 1000, end / 1000);
            for (VoteRecord item : items) {
                row = sheet.createRow(rownum);
                cell = row.createCell(0);
                cell.setCellValue(item.getPeriod());
                if (item.getSum() >= 50)
                    cell.setCellStyle(my_style);
                cell = row.createCell(1);
                cell.setCellValue(item.getSum());
                if (item.getSum() >= 50)
                    cell.setCellStyle(my_style);
                rownum++;
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(centOSExcelPath + name);
//            out = new FileOutputStream(voteExcelPath);
            wb.write(out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                out = null;
            }
        }
    }
}
