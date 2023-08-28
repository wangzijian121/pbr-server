package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.AuthInstitutionAlg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface AuthInstitutionAlgMapper extends BaseMapper<AuthInstitutionAlg> {

    @Select("select *\n" +
            "from (select a.id," +
            " a.auth_type as authType," +
            " i.id as institutionId," +
            " i.name as institutionName," +
            " u.id as userId," +
            " u.nickname as nickname," +
            " alg.id as algorithmId," +
            " alg.name as algorithmName," +
            " a.mark," +
            " a.auth_time as authTime\n" +
            "      from auth_institution_alg a\n" +
            "               left join algorithm alg on a.auth_alg_id = alg.id\n" +
            "               left join user u on a.auth_admin = u.id\n" +
            "               left join institution i on a.institution_id = i.id) res\n" +
            "where (#{keyword} IS NULL OR res.institutionName LIKE CONCAT('%', #{keyword}, '%'))  and  (#{authType}  = -1 OR res.authType =#{authType}) order by id desc")
    Page<Map<String, Object>> selectAuthInstitutionsWithNickname(Page<?> page,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("authType") int authType);

}
