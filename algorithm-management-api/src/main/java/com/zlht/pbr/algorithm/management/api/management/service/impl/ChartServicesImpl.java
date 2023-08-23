package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.zlht.pbr.algorithm.management.api.management.service.ChartServicesI;
import com.zlht.pbr.algorithm.management.api.management.service.PointServicesI;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ChartServicesImpl extends BaseServiceImpl<Charge> implements ChartServicesI {

    private static final Logger logger = LogManager.getLogger(ChartServicesImpl.class);

    @Autowired
    private PointServicesI pointServicesI;

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
        //value
        Map<String, ValueTypeChart> valueTypeChartMap = assemblyValueTypeChartMap(date);
        //pie
        Map<String, List<PieTypeChart>> pieChartDataMap = assemblyPieTypeChartMap(date);
        //line
        Map<String, LineTypeChart> lineTypeChartMap = assemblyLineTypeChartMap();

        //add sheet1
        addSheet4Data(workbook, valueTypeChartMap);

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
        String fileName = LocalDate.now() + "-" + UUID.randomUUID();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
    }

    private Map<String, ValueTypeChart> assemblyValueTypeChartMap(String date) {
        Map<String, ValueTypeChart> valueTypeChartMap = new HashMap<>();
        valueTypeChartMap.put("user_count", pointServicesI.getUserCount(date));
        valueTypeChartMap.put("algorithm_count", pointServicesI.getAlgorithmCount(date));
        valueTypeChartMap.put("algorithm_usage_count", pointServicesI.getAlgorithmUsageCount(date));
        valueTypeChartMap.put("dataset_access_count", pointServicesI.getDatasetAccessCount(date));
        valueTypeChartMap.put("new_action_recognition_category", pointServicesI.newActionRecognitionCategory(date));
        valueTypeChartMap.put("institution_count", pointServicesI.getInstitutionCount(date));
        return valueTypeChartMap;
    }

    private Map<String, List<PieTypeChart>> assemblyPieTypeChartMap(String date) {
        Map<String, List<PieTypeChart>> pieChartDataMap = new HashMap<>();
        pieChartDataMap.put("institution_algorithm_ranking", pointServicesI.getInstitutionAlgorithmRanking());
        return pieChartDataMap;
    }


    private Map<String, LineTypeChart> assemblyLineTypeChartMap() {
        Map<String, LineTypeChart> lineTypeChartMap = new HashMap<>();
        lineTypeChartMap.put("top10_institution_algorithm_usage", pointServicesI.getTop10InstitutionAlgorithmUsage());
        lineTypeChartMap.put("monthly_average_usage_duration", pointServicesI.getMonthlyAverageUserUsageDuration());
        lineTypeChartMap.put("monthly_institution_algorithm_usage_count", pointServicesI.getMonthlyInstitutionAlgorithmUsageCount());
        return lineTypeChartMap;
    }

    void addSheet4Data(Workbook workbook, Map<String, ValueTypeChart> valueTypeChartMap) {
        ValueTypeChart valueTypeChart = valueTypeChartMap.get("user_count");
        Row row = workbook.getSheetAt(3).createRow(2);
        row.createCell(0).setCellValue(valueTypeChart.getToday());
        row.createCell(1).setCellValue(valueTypeChart.getTotal());
    }
}
