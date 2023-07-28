package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Template;

import java.util.Map;

public interface TemplateServicesI {

    /**
     * 查询模板信息
     *
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryTemplateList(int pageNum, int pageSize, int status, String keyword);


    /**
     * 创建模板信息
     *
     * @param wechat
     * @return
     */

    Map<String, Object> createTemplate(Template wechat);

    /**
     * 更新模板信息
     *
     * @param id
     * @param wechat
     * @return
     */
    Map<String, Object> updateTemplate(int id, Template wechat);

    /**
     * 删除模板信息
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteTemplate(int id);

    /**
     * 判断是否已存在
     *
     * @param template
     * @return
     */
    boolean checkSportExistByName(Template template);

    /**
     * 通过模板信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkTemplateExistById(int id);
}