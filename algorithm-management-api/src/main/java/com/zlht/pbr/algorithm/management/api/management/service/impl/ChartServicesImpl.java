package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.zlht.pbr.algorithm.management.api.management.service.ChartServicesI;
import com.zlht.pbr.algorithm.management.api.management.service.DataPointServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.chart.ChartCollect;
import com.zlht.pbr.algorithm.management.dao.chart.LineTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.factory.WorkBookFactory;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ChartServicesImpl extends BaseServiceImpl<Charge> implements ChartServicesI {

    private static final Logger logger = LogManager.getLogger(ChartServicesImpl.class);

    @Autowired
    private DataPointServicesI dataPointServicesI;

    @Override
    public Result getChart(User loginUser, String date) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        //value
        Map<String, ValueTypeChart> valueTypeChartMap = assemblyValueTypeChartMap(date);
        //pie
        Map<String, List<PieTypeChart>> pieChartDataMap = assemblyPieTypeChartMap(date);
        //line
        Map<String, LineTypeChart> lineTypeChartMap = assemblyLineTypeChartMap();

        ChartCollect chartCollect = ChartCollect.builder()
                .valueTypeChartMap(valueTypeChartMap)
                .pieChartDataMap(pieChartDataMap)
                .lineChartDataMap(lineTypeChartMap)
                .build();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(chartCollect);
        return result;
    }

    @Override
    public ResponseEntity downloadChart(User loginUser, String date) {
        if (!canOperator(loginUser)) {
            return (ResponseEntity) ResponseEntity.status(401);
        }
        Workbook workbook = WorkBookFactory.getWorkbook("dataReport");
        //pie
        Map<String, List<PieTypeChart>> pieChartDataMap = assemblyPieTypeChartMap(date);
        //line
        Map<String, LineTypeChart> lineTypeChartMap = assemblyLineTypeChartMap();

        //add sheet1
        addSheet1Data(workbook, assemblyValueTypeChartMap(date));
        //add sheet2
        addSheet2Data(workbook, assemblyValueTypeChartMap(date));
        //add sheet3
        addSheet3Data(workbook, assemblyLineTypeChartMap(), pieChartDataMap);
        //add sheet4
        addSheet4Data(workbook, assemblyValueTypeChartMap(date), assemblyLineTypeChartMap());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] bytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        String fileName = LocalDate.now() + "-" + UUID.randomUUID()+".xlsx";
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
    }

    private Map<String, ValueTypeChart> assemblyValueTypeChartMap(String date) {
        Map<String, ValueTypeChart> valueTypeChartMap = new HashMap<>();
        valueTypeChartMap.put("user_count", dataPointServicesI.getUserCount(date));
        valueTypeChartMap.put("algorithm_count", dataPointServicesI.getAlgorithmCount(date));
        valueTypeChartMap.put("`algorithm_usage_count`", dataPointServicesI.getAlgorithmUsageCount(date));
        valueTypeChartMap.put("dataset_access_count", dataPointServicesI.getDatasetAccessCount(date));
        valueTypeChartMap.put("developer_commit_count", dataPointServicesI.developerCommitCount(date));
        valueTypeChartMap.put("institution_count", dataPointServicesI.getInstitutionCount(date));
        return valueTypeChartMap;
    }

    private Map<String, List<PieTypeChart>> assemblyPieTypeChartMap(String date) {
        Map<String, List<PieTypeChart>> pieChartDataMap = new HashMap<>();
        pieChartDataMap.put("institution_algorithm_ranking", dataPointServicesI.getInstitutionAlgorithmRanking());
        return pieChartDataMap;
    }

    private Map<String, LineTypeChart> assemblyLineTypeChartMap() {
        Map<String, LineTypeChart> lineTypeChartMap = new HashMap<>();
        lineTypeChartMap.put("top10_institution_algorithm_usage", dataPointServicesI.getTop10InstitutionAlgorithmUsage());
        lineTypeChartMap.put("monthly_average_usage_duration", dataPointServicesI.getMonthlyAverageUserUsageDuration());
        lineTypeChartMap.put("monthly_institution_algorithm_usage_count", dataPointServicesI.getMonthlyInstitutionAlgorithmUsageCount());
        return lineTypeChartMap;
    }

    public void addSheet1Data(Workbook workbook, Map<String, ValueTypeChart> valueTypeChartMap) {

        List<String> list = Arrays.asList("user_count", "algorithm_count", "dataset_access_count", "institution_count");
        Row row = workbook.getSheetAt(0).createRow(1);
        CellStyle headerCellStyle = setCellStyle(workbook);

        int j = 0;
        for (String item : list) {
            ValueTypeChart valueTypeChart = valueTypeChartMap.get(item);

            Cell cellToday = row.createCell(j);
            cellToday.setCellValue(valueTypeChart.getToday());
            cellToday.setCellStyle(headerCellStyle);

            Cell cellTotal = row.createCell(j + 1);
            cellTotal.setCellValue(valueTypeChart.getTotal());
            cellTotal.setCellStyle(headerCellStyle);
            j += 2;
        }

    }

    public void addSheet2Data(Workbook workbook, Map<String, ValueTypeChart> map) {

        Row row = workbook.getSheetAt(1).createRow(1);
        CellStyle headerCellStyle = setCellStyle(workbook);
        Cell cellToday = row.createCell(0);
        Cell cellTotal = row.createCell(1);
        cellToday.setCellStyle(headerCellStyle);
        cellTotal.setCellStyle(headerCellStyle);
        cellToday.setCellValue(map.get("developer_commit_count").getToday());
        cellTotal.setCellValue(map.get("developer_commit_count").getTotal());
    }

    public void addSheet3Data(Workbook workbook, Map<String, LineTypeChart> lineTypeChartMap, Map<String, List<PieTypeChart>> pieChartDataMap) {

        CellStyle headerCellStyle = setCellStyle(workbook);
        LineTypeChart lineTypeChart = lineTypeChartMap.get("top10_institution_algorithm_usage");
        List<String> listX = lineTypeChart.getXList();
        List<String> listY = lineTypeChart.getYList().get("algorithm_usage");

        int i = 0;
        for (String index : listX) {
            Row row = workbook.getSheetAt(2).createRow(i + 2);
            Cell cellRanking = row.createCell(0);
            Cell cellNum = row.createCell(1);
            cellRanking.setCellValue(index);
            cellNum.setCellValue(listY.get(i));
            cellRanking.setCellStyle(headerCellStyle);
            cellNum.setCellStyle(headerCellStyle);
            i++;
        }
        List<PieTypeChart> institutionAlgorithmRankingList = pieChartDataMap.get("institution_algorithm_ranking");

        int j = 0;
        for (PieTypeChart pieTypeChart : institutionAlgorithmRankingList) {
            Row row = workbook.getSheetAt(2).getRow(j + 2);
            Cell cellType = row.createCell(2);
            Cell cellNum = row.createCell(3);

            cellType.setCellValue(pieTypeChart.getName());
            cellNum.setCellValue(pieTypeChart.getValue());

            cellType.setCellStyle(headerCellStyle);
            cellNum.setCellStyle(headerCellStyle);
            j++;
        }

    }

    public void addSheet4Data(Workbook workbook, Map<String, ValueTypeChart> valueTypeChartMap, Map<String, LineTypeChart> lineTypeChartMap) {
        List<String> list = Arrays.asList("user_count");
        Row row = workbook.getSheetAt(3).createRow(2);
        CellStyle headerCellStyle = setCellStyle(workbook);

        for (int i = 0; i < list.size(); i++) {
            Cell cellUserCountToday = row.createCell(i);
            cellUserCountToday.setCellStyle(headerCellStyle);
            Cell cellUserCountTotal = row.createCell(i + 1);
            cellUserCountTotal.setCellStyle(headerCellStyle);
            Cell cellDate = row.createCell(i + 2);
            cellDate.setCellStyle(headerCellStyle);

            cellUserCountToday.setCellValue(valueTypeChartMap.get("user_count").getToday());
            cellUserCountTotal.setCellValue(valueTypeChartMap.get("user_count").getTotal());
            cellDate.setCellValue(LocalDate.now().toString());
        }

        List<String> listDate = lineTypeChartMap.get("monthly_average_usage_duration").getXList();
        Map<String, List<String>> map = lineTypeChartMap.get("monthly_average_usage_duration").getYList();
        for (int i = 0; i < listDate.size(); i++) {
            Row rowDate = workbook.getSheetAt(3).getRow(i + 2);
            if (rowDate == null) {
                rowDate = workbook.getSheetAt(3).createRow(i + 2);
            }
            //Date
            Cell cellDate = rowDate.createCell(3);
            cellDate.setCellStyle(headerCellStyle);
            cellDate.setCellValue(i + 1);

            //thisMonthList
            List<String> thisMonthList = map.get("this_month_list");
            if (i < thisMonthList.size()) {
                Cell cellThisMonthList = rowDate.createCell(4);
                cellThisMonthList.setCellStyle(headerCellStyle);
                cellThisMonthList.setCellValue(thisMonthList.get(i));
            }
            //last_month_list
            List<String> last_month_list = map.get("last_month_list");
            if (i < last_month_list.size()) {
                Cell cellLastMonthList = rowDate.createCell(5);
                cellLastMonthList.setCellStyle(headerCellStyle);
                cellLastMonthList.setCellValue(last_month_list.get(i));
            }
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
