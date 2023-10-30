package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zlht.pbr.algorithm.management.api.wechat.service.WxReportDataServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;
import com.zlht.pbr.algorithm.management.dao.mapper.WxReportDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class WxReportDataServiceImpl extends BaseServiceImpl implements WxReportDataServiceI {

    @Autowired
    private WxReportDataMapper wxReportDataMapper;

    @Override
    public void report(String linkCode, String type, int increment) {
        WxReportData wxReportData = null;
        Map mapReportData = null;
        int num = 0;

        Map<String, Integer> resultMap = wxReportDataMapper.getLinkCodeTodayData(linkCode);
        if (resultMap == null) {
            wxReportData = new WxReportData(null, linkCode, 0, 0, 0, new Date());
            wxReportDataMapper.insert(wxReportData);
            mapReportData = BeanUtil.beanToMap(wxReportData);
        } else {
            mapReportData = resultMap;
            num = (int) mapReportData.get(type);
        }

        mapReportData.put(type, num + increment);

        try {
            WxReportData wxReportDataUpdate = BeanUtil.toBean(mapReportData, WxReportData.class);
            wxReportDataMapper.updateById(wxReportDataUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
