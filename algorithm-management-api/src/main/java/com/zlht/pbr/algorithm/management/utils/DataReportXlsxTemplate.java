package com.zlht.pbr.algorithm.management.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Arrays;
import java.util.List;

public class DataReportXlsxTemplate {

    public static Workbook getWorkbook() {
        // 创建一个新的Workbook
        Workbook workbook = new XSSFWorkbook();
        // 算法商家端数据
        Sheet sheet1 = workbook.createSheet("算法商家端数据");
        // 算法开发者数据
        Sheet sheet2 = workbook.createSheet("算法开发者数据");
        //机构数据
        Sheet sheet3 = workbook.createSheet("机构数据");
        //用户数据
        Sheet sheet4 = workbook.createSheet("用户数据");
        loadSheet1(workbook, sheet1);
        loadSheet2(workbook, sheet2);
        loadSheet3(workbook, sheet3);
        loadSheet4(workbook, sheet4);

        // 将Workbook写入到文件系统
  /*      String fileName = "D:\\表格\\" + System.currentTimeMillis() + ".xlsx";
        System.out.println(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
        return workbook;
    }

    public static void loadSheet1(Workbook workbook, Sheet sheet1) {

        CellStyle headerCellStyle = setCellStyle(workbook);
        // 创建一个List来存储你的标题
        List<String> list = Arrays.asList("今日新增算法", "累计算法量", "今日算法使用量", "累计算法使用量",
                "今日数据集接入", "累计数据集接入", "新增体育种类", "累计支持体育种类", "今日机构接入数", "累计机构接入数");
        // 创建第一行并设置其值
        Row headerRow = sheet1.createRow(0);
        for (int i = 0; i < list.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(list.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet1.setColumnWidth(i, 250 * 20);  // 设置列宽为250个字符
        }
    }

    public static void loadSheet2(Workbook workbook, Sheet sheet2) {

        CellStyle headerCellStyle = setCellStyle(workbook);
        // 创建一个List来存储你的标题
        List<String> list = Arrays.asList("今日开发者提交数", "开发者累计提交数");
        // 创建第一行并设置其值
        Row headerRow = sheet2.createRow(0);
        for (int i = 0; i < list.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(list.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet2.setColumnWidth(i, 350 * 20);  // 设置列宽为250个字符
        }
    }

    public static void loadSheet3(Workbook workbook, Sheet sheet3) {

        CellStyle headerCellStyle = setCellStyle(workbook);
        sheet3.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
        // 创建一个List来存储你的标题
        List<String> list = Arrays.asList("机构算法使用次数Top10", "机构购买算法种类排行");
        // 创建第一行并设置其值
        Row headerRow = sheet3.createRow(0);
        for (int i = 0; i < list.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(list.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet3.setColumnWidth(i, 630 * 20);
        }
        // 创建一个List来存储你的标题
        List<String> listOther = Arrays.asList("种类", "购买次数");
        Row row = sheet3.createRow(1);
        for (int i = 0; i < listOther.size(); i++) {
            Cell cell = row.createCell(i + 1);
            cell.setCellValue(listOther.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet3.setColumnWidth(0, 530 * 20);
        }
    }

    public static void loadSheet4(Workbook workbook, Sheet sheet4) {

        sheet4.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
        sheet4.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet4.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        sheet4.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));

        CellStyle headerCellStyle = setCellStyle(workbook);

        // 创建一个List来存储你的标题
        List<String> list = Arrays.asList("今日用户量", "累计用户量", "日期", "月用户平均使用时长（分钟）");

        // 创建第一行并设置其值
        Row headerRow = sheet4.createRow(0);
        for (int i = 0; i < list.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(list.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet4.setColumnWidth(i, 430 * 20);  // 设置列宽为250个字符
        }
        List<String> listOther = Arrays.asList("日期", "本月", "上月");
        Row row = sheet4.createRow(1);
        for (int i = 0; i < listOther.size(); i++) {
            Cell cell = row.createCell(i + listOther.size());
            cell.setCellValue(listOther.get(i));
            cell.setCellStyle(headerCellStyle);
            sheet4.setColumnWidth(0, 430 * 20);  // 设置列宽为250个字符
        }
    }

    private static CellStyle setCellStyle(Workbook workbook) {

        // 创建字体
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setFontName("等线");

        // 创建单元格样式并将字体应用到这个样式
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return headerCellStyle;
    }
}
