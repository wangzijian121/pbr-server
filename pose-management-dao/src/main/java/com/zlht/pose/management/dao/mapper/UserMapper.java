package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zlht.pose.management.dao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username} and  type=#{userType}")
    User queryUserByUserName(@Param("username") String username, @Param("userType") int userType);

    @Select("select *\n" +
            "from user u,\n" +
            "     session s\n" +
            "where u.id = s.user_id\n" +
            "  and s.id = #{sessionId}\n" +
            "  and #{now} < #{expireTime}")
    User queryUserByToken(@Param("sessionId") String sessionId,
                          @Param("expireTime") Date expireTime,
                          @Param("now") Date now);
}
