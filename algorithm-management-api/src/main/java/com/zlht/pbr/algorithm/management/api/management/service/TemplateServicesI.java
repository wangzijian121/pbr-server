package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Template;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface TemplateServicesI {

    /**
     * 查询模板信息
     *
     * @param loginUser
     * @param currentPage
     * @param pageSize
     * @param status
     * @param keyword
     * @return
     */
    Result<PageInfo<Template>> queryTemplateList(User loginUser, int currentPage, int pageSize, int status, String keyword);

    /**
     * 查询已添加的体育
     *
     * @param loginUser
     * @return
     */
    Result<Template> queryTemplateMap(User loginUser);

    /**
     * 创建模板信息
     *
     * @param loginUser
     * @param wechat
     * @return
     */

    Map<String, Object> createTemplate(User loginUser, Template wechat);

    /**
     * 更新模板信息
     *
     * @param loginUser
     * @param id
     * @param wechat
     * @return
     */
    Map<String, Object> updateTemplate(User loginUser, int id, Template wechat);

    /**
     * 删除模板信息
     *
     * @param loginUser
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
