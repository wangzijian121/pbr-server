package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zi jian Wang
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 校验资源存在
     *
     * @param uuid
     * @return
     */
    @Select("select *  from  resources " +
            "where" +
            " alias =#{uuid}  " +
            "limit 1 ")
    Resource resourceExist(@Param("uuid") String uuid);
}
