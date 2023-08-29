package com.zlht.pbr.algorithm.management.dao.chart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueTypeChart {

    @JsonProperty("today")
    private String today;

    @JsonProperty("total")
    private String total;

}