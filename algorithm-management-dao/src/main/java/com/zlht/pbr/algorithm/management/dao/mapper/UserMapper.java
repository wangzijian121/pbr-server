package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @author zi jian Wang
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 按用户名查询用户
     *
     * @param username
     * @param userType
     * @return
     */
    @Select("select * from user where username = #{username} and  type=#{userType} order by id desc ")
    User queryUserByUserName(@Param("username") String username, @Param("userType") int userType);

    /**
     * 查询令牌用户
     *
     * @param sessionId
     * @param expireTime
     * @param now
     * @return
     */
    @Select("select *\n" +
            "from user u,\n" +
            "     session s\n" +
            "where u.id = s.user_id\n" +
            "  and s.id = #{sessionId}\n" +
            "  and #{now} < #{expireTime} ")
    User queryUserByToken(@Param("sessionId") String sessionId,
                          @Param("expireTime") Date expireTime,
                          @Param("now") Date now);


}
