package com.zlht.pbr.algorithm.management.factory;

import com.zlht.pbr.algorithm.management.utils.DataReportXlsxTemplate;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author zi jian Wang
 */
public class WorkBookFactory {

    public static Workbook getWorkbook(String type) {
        String dataReport = "dataReport";
        String other = "other";
        if (dataReport.equalsIgnoreCase(type)) {
            return DataReportXlsxTemplate.getWorkbook();
        } else if (other.equalsIgnoreCase(type)) {
            return new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("Invalid type. Expected 'xls' or 'xlsx'.");
        }
    }

}
