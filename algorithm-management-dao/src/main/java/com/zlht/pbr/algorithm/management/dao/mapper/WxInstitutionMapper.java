package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ziji Wang
 */
public interface WxInstitutionMapper extends BaseMapper<Algorithm> {


    /**
     * 获取机构授权算法
     *
     * @return
     */
    @Select("select *\n" +
            "from (select wc.app_id as appId,\n" +
            "             a.id  as algorithmId,\n" +
            "             a.name,\n" +
            "             (select sc.name from sport_category sc where sc.id = a.sport_category) as sportCategory,\n" +
            "             (SELECT t.content\n" +
            "              FROM template t\n" +
            "              WHERE t.id = a.template_id)  AS content\n" +
            "      from wechat wc\n" +
            "               left join institution i on wc.institution_id = i.id\n" +
            "               left join auth_institution_alg aig on aig.institution_id = i.id\n" +
            "               left join algorithm a on a.id = aig.auth_alg_id) res\n" +
            "where  appId is not  null and res.algorithmId  is not null\n")
    List<Map<String, Object>> getInstitutionAlgorithm();


    /**
     * 获取机构链接代码与appID 的映射
     *
     * @return
     */
    @Select("select link_code as linkCode ,  app_id as appId ,app_secret as appSecret  from wechat")
    List<Map<String, Object>> getInstitutionLinkCodeAppIdMap();
}

