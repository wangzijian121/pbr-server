package com.zlht.pose.management.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
     * full name 123.zip
     */
    private String fullName;

    /**
     * alias 834bb3d8-6cf1-4d2d-9351-b0a1698f2839
     */
    private String alias;

    /**
     *suffix : .zip
     */
    private String suffix;
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

    public Resource(String fullName, String alias, String  suffix, long size, Date createTime, Date updateTime) {

        this.fullName = fullName;
        this.alias = alias;
        this.suffix=suffix;
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
