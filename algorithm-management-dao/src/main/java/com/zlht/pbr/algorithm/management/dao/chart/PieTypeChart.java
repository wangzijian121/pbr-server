package com.zlht.pbr.algorithm.management.dao.chart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieTypeChart {

    @JsonProperty("value")
    private String value;

    @JsonProperty("name")
    private String name;

}