package com.zlht.pose.management.dao.chart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChartCollect {

    @JsonProperty("valueData")
    private Map<String, ValueTypeChart> valueTypeChartMap;

    @JsonProperty("pieChartData")
    private Map<String, List<PieTypeChart>> pieChartDataMap;

    @JsonProperty("lineChartData")
    private Map<String, LineTypeChart> lineChartDataMap;
}
