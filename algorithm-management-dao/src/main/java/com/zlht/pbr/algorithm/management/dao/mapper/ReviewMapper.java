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
            "             dr.commit_name as commitName,\n" +
            "             dr.developer_id as  developerId,\n" +
            "             dr.type,\n" +
            "             u.id as userId,\n" +
            "             u.nickname,\n" +
            "             dr.sport_type as sportType,\n" +
            "             dr.file as fileUuid,\n" +
            "             r.full_name as fileName,\n" +
            "             dr.docs,\n" +
            "             dr.demo,\n" +
            "             dr.mark,\n" +
            "             dr.status,\n" +
            "             dr.create_time as createTime\n" +
            "      from developer_review dr\n" +
            "               left join resources r  on r.alias = dr.file\n" +
            "               left join user u on dr.developer_id = u.id) res \n" +
            "where   (#{keyword} IS NULL OR res.nickname LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> selectReview(Page<?> page,
                                           @Param("keyword") String keyword);

    @Select("select * from (select dr.id,\n" +
            "             dr.commit_name as commitName,\n" +
            "             dr.developer_id as developerId ,\n" +
            "             u.id as userId,\n" +
            "             u.nickname,\n" +
            "             dr.type,\n" +
            "             dr.sport_type as sportType,\n" +
            "             dr.file as fileUuid,\n" +
            "             r.full_name as fileName,\n" +
            "             dr.docs,\n" +
            "             dr.demo,\n" +
            "             dr.mark,\n" +
            "             dr.status,\n" +
            "             dr.create_time as createTime\n" +
            "      from developer_review dr\n" +
            "               left join resources r  on r.alias = dr.file\n" +
            "               left join user u on dr.developer_id = u.id) res \n" +
            "where   (#{keyword} IS NULL OR res.commit_name LIKE CONCAT('%', #{keyword}, '%')) and developer_id =#{developerId} and res.type in ( ${type} ) order by id desc")
    Page<Map<String, Object>> selectDeveloperReview(Page<?> page,
                                                    @Param("keyword") String keyword,
                                                    @Param("developerId") int developerId,
                                                    @Param("type") String type);

    @Update("update developer_review " +
            "set status=#{status} , " +
            "mark=#{mark} " +
            "where id =#{id}")
    int updateReviewStatus(@Param("id") int id,
                           @Param("status") int status,
                           @Param("mark") String mark);

    @Select("select id,commit_name as commitName from  developer_review  group by id,commit_name")
    List<Map<String, Object>> queryReviewMap();


}
