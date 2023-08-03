package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ResourceMapper extends BaseMapper<Resource> {

    @Select("select *\n" +
            "from `pose-management`.resources\n" +
            "where user_id = #{userId}\n" +
            "  and full_name = #{fullName} limit 1")
    Resource resourceExist(@Param("userId") int userId, @Param("fullName") String fullName);


}
