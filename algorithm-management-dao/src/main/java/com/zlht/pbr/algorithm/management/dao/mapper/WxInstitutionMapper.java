package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("SELECT a.*\n" +
            "FROM algorithm a\n" +
            "WHERE EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM auth_institution_alg aig\n" +
            "    WHERE a.id = aig.auth_alg_id\n" +
            "    AND aig.institution_id = (\n" +
            "        SELECT institution_id\n" +
            "        FROM wx_report_data wrd\n" +
            "        LEFT JOIN wechat w ON w.wechat_id = wrd.app_id\n" +
            "        WHERE app_id = #{appId}\n" +
            "    )\n" +
            ");")
    List<Algorithm> getInstitutionAlgorithm(@Param("appId") String appId);


}

