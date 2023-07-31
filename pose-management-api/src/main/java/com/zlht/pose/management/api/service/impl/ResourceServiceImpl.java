package com.zlht.pose.management.api.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlht.pose.management.api.service.ResourceServiceI;
import com.zlht.pose.management.dao.entity.Resource;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceServiceI {


    private final String[] ACCEPT_TYPES = new String[]{"zip"};
    /**
     * 文件磁盘路径
     */
    @Value("${files.upload.local.path}")
    private String fileUploadPath;

    @Value("${files.upload.local.maxFileSize}")
    private long maxFileSize;

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Map<String, Object> createResource(MultipartFile file) {

        Map<String, Object> map = new HashMap<>();
        //获取文件原始名称
        String fullName = file.getOriginalFilename();

        //获取文件的类型
        String type = FileUtil.extName(fullName);
        if (!checkFileType(type)) {
            putMsg(map, 400, "不支持的文件类型！");
            return map;
        }
        long size = file.getSize();
        if (!checkFileSize(size)) {
            putMsg(map, 400, "文件过大！");
            return map;
        }
        User user = new User();
        user.setId(1);
        user.setType(1);
        String uuid = UUID.randomUUID().toString();
        String fileName = fileUploadPath + uuid + StrUtil.DOT;
        Resource resource = new Resource(user.getType(), user.getId(), fullName, uuid,
                "这是王子健上传的文件！", size, new Date(), new Date());
        if (resourceExist(user, fullName)) {

            UpdateWrapper<Resource> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("full_name", resource.getFullName())
                    .set("alias", resource.getAlias())
                    .set("description", resource.getDescription())
                    .set("size", resource.getSize())
                    .set("update_time", resource.getUpdateTime());

            resourceMapper.update(null, updateWrapper);
        } else {
            resourceMapper.insert(resource);
        }
        File uploadResource = new File(fileName + type);
        //将临时文件转存到指定磁盘位置
        try {
            file.transferTo(uploadResource);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public int deleteResource(String name) {
        return 0;
    }

    @Override
    public org.springframework.core.io.Resource downloadResource(String resourceId) {

        return null;
    }

    @Override
    public boolean checkFileType(String type) {
        if (type == null || type.equals("")) return false;
        for (String item : ACCEPT_TYPES) {
            if (item.equals(type.toLowerCase())) return true;
        }
        return false;
    }

    @Override
    public boolean checkFileSize(long size) {

        return size < maxFileSize ? true : false;
    }


    @Override
    public boolean resourceExist(User user, String fullName) {
        return resourceMapper.resourceExist(user.getType(), user.getId(), fullName) != null;
    }
}