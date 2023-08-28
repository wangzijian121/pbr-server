package com.zlht.pbr.algorithm.management.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "institution", autoResultMap = true)
public class Institution {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(name = "type", value = "机构类型", required = true)
    private Integer type;


    @ApiModelProperty(name = "name", value = "机构名", required = true)
    private String name;


    @ApiModelProperty(name = "phone", value = "手机", required = true)
    private String phone;


    @ApiModelProperty(name = "email", value = "邮箱", required = true)
    private String email;


    @ApiModelProperty(name = "address", value = "地址", required = true)
    private String address;


    @ApiModelProperty(name = "map", value = "地图位置", required = false)
    private String map;

    @TableField(value = "create_time")
    @ApiModelProperty(name = "create_time", value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;


}