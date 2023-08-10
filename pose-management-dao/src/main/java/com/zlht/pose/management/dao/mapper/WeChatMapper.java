package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.WeChat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface  WeChatMapper extends BaseMapper<WeChat> {


    @Select("select *\n" +
            "from (select wc.id, wc.wechat_id, wc.name as name , i.name as institution_name, wc.status, wc.mark, wc.create_time\n" +
            "      from wechat wc" +
            "               left join institution i on i.id = wc.institution_id) res\n" +
            " where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%'))  and  (#{status}  = -1 OR res.status =#{status}) ")
    Page<Map<String, Object>> selectWechat(Page<?> page,
                                           @Param("keyword") String keyword,
                                           @Param("status") int status);
}
