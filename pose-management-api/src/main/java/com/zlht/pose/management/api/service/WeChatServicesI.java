package com.zlht.pose.management.api.service;

import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.entity.WeChat;

import java.util.Map;

public interface WeChatServicesI {

    /**
     * 查询小程序信息
     *
     * @param status
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<WeChat>> queryWeChatList(User loginUser, int currentPage, int pageSize, int status, String keyword);


    /**
     * 创建小程序信息
     *
     * @param wechat
     * @return
     */

    Map<String, Object> createWeChat(User loginUser, WeChat wechat);

    /**
     * 更新小程序信息
     *
     * @param id
     * @param wechat
     * @return
     */
    Map<String, Object> updateWeChat(User loginUser, int id, WeChat wechat);

    /**
     * 删除小程序信息
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteWeChat(User loginUser, int id);

    /**
     * 判断是否已存在
     *
     * @param weChat
     * @return
     */
    boolean checkSportExistByName(WeChat weChat);

    /**
     * 通过小程序信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkWeChatExistById(int id);
}
