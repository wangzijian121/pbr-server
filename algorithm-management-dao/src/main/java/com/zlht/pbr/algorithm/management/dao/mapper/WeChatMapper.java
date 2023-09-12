package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.WeChat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface WeChatMapper extends BaseMapper<WeChat> {


    /**
     * 查询小程序
     *
     * @param page
     * @param keyword
     * @param status
     * @return
     */
    @Select("select * \n" +
            "from (select wc.id, " +
            "wc.link_code as linkCode,  " +
            "wc.app_id as appId,  " +
            "wc.name as name ," +
            " i.id as institutionId," +
            " i.name as institutionName," +
            " wc.status, " +
            "wc.mark, " +
            "wc.create_time as createTime\n" +
            "from wechat wc" +
            " left join institution i on i.id = wc.institution_id) res\n" +
            " where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%'))  and  (#{status}  = -1 OR res.status =#{status})  order by id desc")
    Page<Map<String, Object>> selectWechat(Page<?> page,
                                           @Param("keyword") String keyword,
                                           @Param("status") int status);

}
