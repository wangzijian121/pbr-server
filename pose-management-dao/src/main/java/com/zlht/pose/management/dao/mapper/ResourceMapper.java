package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ResourceMapper extends BaseMapper<Resource> {

    @Select("select *  from  resources " +
            "where" +
            " upload_user_type=#{uploadUserType} " +
            "and  user_id =#{userId} " +
            "and  full_name =#{fullName}  " +
            "limit 1 ")
    Resource resourceExist(@Param("uploadUserType") int upload_user_type, @Param("userId") int userId, @Param("fullName") String fullName);


}
