package com.zlht.pose.developer.api.service;

import com.zlht.pose.management.dao.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DeveloperResourceServiceI {
    /**
     * 上传
     */
    Map<String, Object> createResource(User loginUser, MultipartFile file);


    /**
     * 下载文件
     */
    ResponseEntity downloadResource(User loginUser, String uuid);

    /**
     * 校验资源类型
     */
    boolean checkFileType(String type);

    /**
     * 校验资源大小
     */
    boolean checkFileSize(long size);

    /**
     * 判断资源元数据 是否存在
     */
    boolean resourceExist(String fullName);

}
