package com.zlht.pose.management.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sport_category", autoResultMap = true)
public class Sport {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;


    @ApiModelProperty(value = "体育类型(0:学校体育 1:群众体育 2:竞技体育)", required = true)
    private Integer type;


    @ApiModelProperty(value = "体育名", required = true)
    private String name;


    @ApiModelProperty(value = "添加人", required = true)
    private int userId;


    @ApiModelProperty(value = "备注", required = false)
    private String mark;


    @ApiModelProperty(value = "授权时间", required = true)
    private Date createTime;


}