package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface ReviewMapper extends BaseMapper<Review> {


    @Select("select * from (select dr.id,\n" +
            "             dr.commit_name,\n" +
            "             u.nickname,\n" +
            "             dr.type,\n" +
            "             dr.file,\n" +
            "             dr.demo,\n" +
            "             dr.mark,\n" +
            "             dr.status,\n" +
            "             dr.create_time\n" +
            "      from developer_review dr\n" +
            "               left join user u on dr.developer_id = u.id) res \n" +
            "where   (#{keyword} IS NULL OR res.nickname LIKE CONCAT('%', #{keyword}, '%'))")
    Page<Map<String, Object>> selectReview(Page<?> page,
                                           @Param("keyword") String keyword);

    @Update("update developer_review " +
            "set status=#{status} , " +
            "mark=#{mark} " +
            "where id =#{id}")
    int updateReviewStatus(@Param("id") int id,
                           @Param("status") int status,
                           @Param("mark") String mark);
}
