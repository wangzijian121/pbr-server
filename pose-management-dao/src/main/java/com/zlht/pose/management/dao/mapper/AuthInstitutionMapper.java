package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.AuthInstitution;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


public interface AuthInstitutionMapper extends BaseMapper<AuthInstitution> {

    @Select("select * from (select a.id, a.auth_type, a.institution_name, u.nickname as auth_admin, sc.name, a.mark, a.auth_time " +
            "from auth_institution a " +
            "left join sport_category sc on a.auth_content = sc.id " +
            "left join user u on a.auth_admin = u.id) res " +
            " where (#{institution_name} is NULL OR res.institution_name LIKE CONCAT('%', #{institution_name}, '%')) AND " +
            "    (#{auth_type} =-1  OR res.auth_type = #{auth_type})")
    Page<Map<String, Object>> selectAuthInstitutionsWithNickname(Page<?> page,
                                                                 @Param("institution_name") String institution_name,
                                                                 @Param("auth_type") int auth_type);

}
