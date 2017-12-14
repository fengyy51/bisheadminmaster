package com.binwang.util.excelToSql.service;

import com.binwang.util.excelToSql.bean.Collect;
import com.binwang.util.excelToSql.bean.Prize;
import com.binwang.util.excelToSql.dao.ICollectInfoDao;
import com.binwang.util.excelToSql.dao.IPrizeExcelDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Iterator;

/**
 * Created by owen on 17/8/7.
 */
@Service
public class HandlePrize {

//    private String filePath = "/Users/owen/Desktop/prize.xlsx";

    private String CollectFilePath = "/Users/owen/Desktop/collect_info_test.xlsx";

    @Resource
    private IPrizeExcelDao prizeDao;

    @Resource
    private ICollectInfoDao collectDao;

    public Boolean doCollectInfo() {
        try {
            OPCPackage pkg = OPCPackage.open(new File(CollectFilePath));
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            rows.next();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Collect c = new Collect();
                int count = 1;
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext() && count <= 5) {
                    XSSFCell cell = (XSSFCell) cells.next();
                    switch (count) {
                        case 1:
                            c.setId((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            c.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            c.setBrand_name(cell.getStringCellValue());
                            break;
                        case 4:
                            c.setMobile(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case 5:
                            c.setIntro(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    count++;
                }
//                System.out.println(c.getId());
                collectDao.updateInfo(c);
            }
            System.out.println("over");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void inputStreamToFile(InputStream ins,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Boolean doService(MultipartFile file) {
        try {
            File f = null;
            if (file.equals("") || file.getSize() <= 0) {
                file = null;
                return false;
            } else {
                InputStream ins = file.getInputStream();
                f = new File(file.getOriginalFilename());
                inputStreamToFile(ins, f);
//                System.out.println(f.getPath());
                OPCPackage pkg = OPCPackage.open(f.getPath());
                XSSFWorkbook wb = new XSSFWorkbook(pkg);
                XSSFSheet sheet = wb.getSheetAt(0);
                Iterator<Row> rows = sheet.rowIterator();
                rows.next();
                while (rows.hasNext()) {
                    XSSFRow row = (XSSFRow) rows.next();
                    Prize p = new Prize();
                    int count = 1;
                    Iterator<Cell> cells = row.cellIterator();
                    while (cells.hasNext() && count <= 8) {
                        XSSFCell cell = (XSSFCell) cells.next();
                        switch (count) {
                            case 1:
//                                System.out.println(cell.getNumericCellValue());
                                p.setRelationId((int) cell.getNumericCellValue());
                                break;
                            case 2:
//                                System.out.println(cell.getStringCellValue());
                                p.setName(cell.getStringCellValue());
                                break;
                            case 3:
                                p.setInfo(cell.getStringCellValue());
                                break;
                            case 4:
                                p.setRatio((int) cell.getNumericCellValue());
                                break;
                            case 5:
                                p.setNum((int) cell.getNumericCellValue());
                                break;
                            case 6:
                                p.setType((int) cell.getNumericCellValue());
                                break;
                            case 7:
                                p.setDuijiang_time(cell.getStringCellValue());
                                break;
                            case 8:
                                p.setDuijiang_loc(cell.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                    System.out.println(p.getRelationId());
                    System.out.println(p.getName());
                    System.out.println(p.getInfo());
                    System.out.println(p.getNum());
                    System.out.println(p.getDuijiang_time());
                    System.out.println(p.getDuijiang_loc());
                    int num=prizeDao.getPrize(p.getRelationId(),p.getName());
                    if(num>0){
                        System.out.println("update");
                        int res=prizeDao.updatePrize(p);
                        System.out.println(res);
                        if(res>0){
                            System.out.println("update success");
                        }
                        else {
                            System.out.println("update error");
                        }
                    }else {
                        System.out.println("insert");
                        int res=prizeDao.insertPrize(p);
                        if(res>0){
                            System.out.println("insert success");
                        }
                        else {
                            System.out.println("insert error");
                        }
                    }

                }
                File del = new File(f.toURI());
                del.delete();
                System.out.println("over");
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            return false;

        }
    }
}
