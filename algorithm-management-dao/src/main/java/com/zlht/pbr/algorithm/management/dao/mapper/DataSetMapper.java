package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.DataSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface DataSetMapper extends BaseMapper<DataSet> {
    /**
     * 查询数据集
     *
     * @param page
     * @param keyword
     * @param type
     * @return
     */
    @Select("select *\n" +
            "from (select ds.id,\n" +
            "             ds.name,\n" +
            "             ds.type,\n" +
            "             sc.id as sportCategoryId,\n" +
            "             sc.name as sportCategoryName,\n" +
            "             ds.file as fileUuid,\n" +
            "             r.full_name as fileName,\n" +
            "             ds.demo,\n" +
            "             ds.install_type as installType,\n" +
            "             u.id as userId,\n" +
            "             u.nickname,\n" +
            "             ds.create_time as createTime\n" +
            "      from data_set ds\n" +
            "               left join user u on ds.uploader = u.id\n" +
            "               left join resources r  on r.alias = ds.file\n" +
            "               left join sport_category sc on ds.sport_category = sc.id) res\n " +
            "where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.type =#{type})")
    Page<Map<String, Object>> selectDataSet(Page<?> page, @Param("keyword") String keyword,
                                            @Param("type") int type);
}

