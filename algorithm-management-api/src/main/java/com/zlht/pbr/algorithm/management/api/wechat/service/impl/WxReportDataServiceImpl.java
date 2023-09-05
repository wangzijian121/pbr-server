package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxReportDataServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;
import com.zlht.pbr.algorithm.management.dao.mapper.WxReportDataMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class WxReportDataServiceImpl extends BaseServiceImpl implements WxReportDataServiceI {

    private final static Logger logger = LogManager.getLogger(WxReportDataServiceImpl.class);

    @Autowired
    private WxReportDataMapper wxReportDataMapper;

    @Override
    public Map<String, Object> report(WxReportData wxReportData) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            wxReportDataMapper.insert(wxReportData);
            logger.info("report() method. wxReportData={}", wxReportData);
            putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        } catch (Exception e) {
            String errMsg = "上报数据失败";
            logger.error("report() method .message={}, wxReportData={}", errMsg, wxReportData, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }
}
