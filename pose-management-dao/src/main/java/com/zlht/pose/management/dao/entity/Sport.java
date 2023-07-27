package com.zlht.pose.management.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer id;


    @ApiModelProperty(name = "type", value = "体育类型(0:学校体育 1:群众体育 2:竞技体育)", required = true)
    private Integer type;


    @ApiModelProperty(name = "name", value = "体育名", required = true)
    private String name;


    @ApiModelProperty(name = "creator", value = "添加人", required = true)
    private String creator;



    @ApiModelProperty(name = "mark", value = "备注", required = false)
    private String mark;


    @ApiModelProperty(name = "createTime", value = "授权时间", required = true)
    private Date createTime;


}