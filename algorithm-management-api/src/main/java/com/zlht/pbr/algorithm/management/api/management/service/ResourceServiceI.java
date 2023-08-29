package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ResourceServiceI {
    /**
     * 上传
     *
     * @param loginUser
     * @param file
     * @return
     */
    Map<String, Object> createResource(User loginUser, MultipartFile file);

    /**
     * 删除文件
     *
     * @param loginUser
     * @param uuid
     * @return
     */
    Map<String, Object> deleteResource(User loginUser, String uuid);


    /**
     * 下载文件
     *
     * @param loginUser
     * @param uuid
     * @return
     */
    ResponseEntity downloadResource(User loginUser, String uuid);

    /**
     * 校验资源类型
     *
     * @param type
     * @return
     */
    boolean checkFileType(String type);

    /**
     * 校验资源大小
     *
     * @param size
     * @return
     */
    boolean checkFileSize(long size);

    /**
     * 判断资源元数据 是否存在
     *
     * @param fullName
     * @return
     */
    boolean resourceExist(String fullName);

}
