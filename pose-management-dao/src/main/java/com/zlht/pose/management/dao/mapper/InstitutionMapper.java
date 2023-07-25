package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Institution;
import com.zlht.pose.management.dao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface InstitutionMapper extends BaseMapper<Institution> {

/*    @Select("select * from institution where name = #{name}")
    User queryUserByUserName(@Param("name") String name);*/
}
