package com.zlht.pose.management.dao.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@NoArgsConstructor
@TableName("resources")
public class Resource {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * upload user type
     */
    @TableField
    private Integer uploadUserType;

    /**
     * user id
     */
    @TableField
    private Integer userId;

    /**
     * full name 123.zip
     */
    private String fullName;

    /**
     * full name 834bb3d8-6cf1-4d2d-9351-b0a1698f2839
     */
    private String alias;

    /**
     * description
     */
    private String description;


    /**
     * resource size
     */
    private long size;

    /**
     * create time
     */
    @ApiModelProperty(value = "录入时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;

    /**
     * update time
     */
    @ApiModelProperty(value = "更新时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date updateTime;

    public Resource(Integer uploadUserType, Integer userId, String fullName, String alias, String description, long size, Date createTime, Date updateTime) {
        this.uploadUserType = uploadUserType;
        this.userId = userId;
        this.fullName = fullName;
        this.alias = alias;
        this.description = description;
        this.size = size;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource resource = (Resource) o;

        if (id != resource.id) {
            return false;
        }
        return alias.equals(resource.alias);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + alias.hashCode();
        return result;
    }
}
