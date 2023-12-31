package com.zlht.pbr.algorithm.management.dao.chart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Builder
@Data
public class ChartCollect {

    @JsonProperty("valueData")
    private Map<String, ValueTypeChart> valueTypeChartMap;

    @JsonProperty("pieChartData")
    private Map<String, List<PieTypeChart>> pieChartDataMap;

    @JsonProperty("lineChartData")
    private Map<String, LineTypeChart> lineChartDataMap;


}
