package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.AuthInstitutionAlg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface AuthInstitutionAlgMapper extends BaseMapper<AuthInstitutionAlg> {

    @Select("select *\n" +
            "from (select a.id, a.auth_type," +
            " i.id as institution_id," +
            " i.name as institution_name," +
            " u.id as user_id," +
            " u.nickname as nickname," +
            " alg.id as algorithm_id," +
            " alg.name as algorithm_name," +
            " a.mark," +
            " a.auth_time\n" +
            "      from auth_institution_alg a\n" +
            "               left join algorithm alg on a.auth_alg_id = alg.id\n" +
            "               left join user u on a.auth_admin = u.id\n" +
            "               left join institution i on a.institution_id = i.id) res\n" +
            "where (#{keyword} IS NULL OR res.institution_name LIKE CONCAT('%', #{keyword}, '%'))  and  (#{auth_type}  = -1 OR res.auth_type =#{auth_type})")
    Page<Map<String, Object>> selectAuthInstitutionsWithNickname(Page<?> page,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("auth_type") int auth_type);

}
