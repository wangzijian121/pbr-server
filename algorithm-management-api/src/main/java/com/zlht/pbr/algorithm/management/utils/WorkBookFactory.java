package com.zlht.pbr.algorithm.management.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkBookFactory {

    public static Workbook getWorkbook(String type) {
        if (type.equalsIgnoreCase("dataReport")) {
            return DataReportXlsxTemplate.getWorkbook();
        } else if (type.equalsIgnoreCase("other")) {
            return new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("Invalid type. Expected 'xls' or 'xlsx'.");
        }
    }

}
