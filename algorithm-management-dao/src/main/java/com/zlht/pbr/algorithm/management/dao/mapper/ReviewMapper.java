package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ReviewMapper extends BaseMapper<Review> {


    @Select("select * from (select dr.id,\n" +
            "             dr.commit_name,\n" +
            "             dr.developer_id,\n" +
            "             u.id as user_id,\n" +
            "             u.nickname,\n" +
            "             dr.type,\n" +
            "             dr.file as file_uuid,\n" +
            "             r.full_name as file_name,\n" +
            "             dr.demo,\n" +
            "             dr.mark,\n" +
            "             dr.status,\n" +
            "             dr.create_time\n" +
            "      from developer_review dr\n" +
            "               left join resources r  on r.alias = dr.file\n" +
            "               left join user u on dr.developer_id = u.id) res \n" +
            "where   (#{keyword} IS NULL OR res.nickname LIKE CONCAT('%', #{keyword}, '%'))")
    Page<Map<String, Object>> selectReview(Page<?> page,
                                           @Param("keyword") String keyword);

    @Select("select * from (select dr.id,\n" +
            "             dr.commit_name,\n" +
            "             dr.developer_id,\n" +
            "             u.id as user_id,\n" +
            "             u.nickname,\n" +
            "             dr.type,\n" +
            "             dr.file as file_uuid,\n" +
            "             r.full_name as file_name,\n" +
            "             dr.demo,\n" +
            "             dr.mark,\n" +
            "             dr.status,\n" +
            "             dr.create_time\n" +
            "      from developer_review dr\n" +
            "               left join resources r  on r.alias = dr.file\n" +
            "               left join user u on dr.developer_id = u.id) res \n" +
            "where   (#{keyword} IS NULL OR res.commit_name LIKE CONCAT('%', #{keyword}, '%')) and developer_id =#{developerId} ")
    Page<Map<String, Object>> selectDeveloperReview(Page<?> page,
                                                    @Param("keyword") String keyword,
                                                    @Param("developerId") int developerId);

    @Update("update developer_review " +
            "set status=#{status} , " +
            "mark=#{mark} " +
            "where id =#{id}")
    int updateReviewStatus(@Param("id") int id,
                           @Param("status") int status,
                           @Param("mark") String mark);

    @Select("select id,commit_name from  developer_review  group by id,commit_name")
    List<Map<String, Object>> queryReviewMap();


}
