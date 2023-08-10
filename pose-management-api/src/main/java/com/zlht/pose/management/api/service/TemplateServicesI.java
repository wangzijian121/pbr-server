package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Template;
import com.zlht.pose.management.dao.entity.User;

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
    Result queryTemplateList(User loginUser, int pageNum, int pageSize, int status, String keyword);


    /**
     * 创建模板信息
     *
     * @param wechat
     * @return
     */

    Map<String, Object> createTemplate(User loginUser, Template wechat);

    /**
     * 更新模板信息
     *
     * @param id
     * @param wechat
     * @return
     */
    Map<String, Object> updateTemplate(User loginUser, int id, Template wechat);

    /**
     * 删除模板信息
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteTemplate(User loginUser, int id);

    /**
     * 通过模板信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkTemplateExistById(int id);
}
