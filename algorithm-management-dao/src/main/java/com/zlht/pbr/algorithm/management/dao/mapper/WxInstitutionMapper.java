package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
     * @param appId
     * @return
     */
    @Select("SELECT a.id as algorithmId,a.name\n" +
            "     , (select sc.name from sport_category sc where sc.id = a.sport_category) as sportCategory\n" +
            "     , (SELECT t.content\n" +
            "        FROM template t\n" +
            "        WHERE t.id = a.template_id)                                           AS content\n" +
            "FROM algorithm a\n" +
            "WHERE EXISTS (SELECT 1\n" +
            "              FROM auth_institution_alg aig\n" +
            "              WHERE a.id = aig.auth_alg_id\n" +
            "                AND aig.institution_id = (SELECT institution_id\n" +
            "                                          FROM wx_report_data wrd\n" +
            "                                                   LEFT JOIN wechat w ON w.wechat_id = wrd.app_id\n" +
            "                                          WHERE app_id = #{appId}));")
    List<Map<String, Object>> getInstitutionAlgorithm(@Param("appId") String appId);


}

