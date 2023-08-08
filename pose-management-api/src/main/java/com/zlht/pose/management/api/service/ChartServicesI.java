package com.zlht.pose.management.api.service;

import com.zlht.pose.management.dao.chart.ChartCollect;

import javax.xml.crypto.Data;

public interface ChartServicesI {

    /**
     * 查询图表
     *
     */
    ChartCollect  getChart(Data now);
}
