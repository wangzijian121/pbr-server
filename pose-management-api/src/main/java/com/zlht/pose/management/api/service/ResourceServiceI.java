package com.zlht.pose.management.api.service;

import com.zlht.pose.management.dao.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourceServiceI {
    /**
     * 上传
     */
    Map<String, Object> createResource(MultipartFile file);

    /**
     * 删除文件
     */
    int deleteResource(String name);

    /**
     * 下载文件
     */
    org.springframework.core.io.Resource downloadResource(String resourceId);

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
    boolean resourceExist(User user, String fullName);

}
