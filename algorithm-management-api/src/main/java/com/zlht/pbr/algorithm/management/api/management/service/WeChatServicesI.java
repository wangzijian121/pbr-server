package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.entity.WeChat;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface WeChatServicesI {

    /**
     * 查询小程序信息
     *
     * @param loginUser
     * @param currentPage
     * @param pageSize
     * @param status
     * @param keyword
     * @return
     */
    Result<PageInfo<WeChat>> queryWeChatList(User loginUser, int currentPage, int pageSize, int status, String keyword);


    /**
     * 创建小程序信息
     *
     * @param loginUser
     * @param wechat
     * @return
     */

    Map<String, Object> createWeChat(User loginUser, WeChat wechat);

    /**
     * 更新小程序信息
     *
     * @param loginUser
     * @param id
     * @param wechat
     * @return
     */
    Map<String, Object> updateWeChat(User loginUser, int id, WeChat wechat);

    /**
     * 删除小程序信息
     *
     * @param loginUser
     * @param id
     * @return
     */
    Map<String, Object> deleteWeChat(User loginUser, int id);


    /**
     * 通过小程序信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkWeChatExistById(int id);
}
