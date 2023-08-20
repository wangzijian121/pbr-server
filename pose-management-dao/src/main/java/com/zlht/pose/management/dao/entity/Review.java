package com.zlht.pose.management.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName(value = "developer_review", autoResultMap = true)
public class Review {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;


    @ApiModelProperty(value = "提交名", required = true)
    private String commitName;


    @ApiModelProperty(value = "开发者ID", required = true)
    private Integer developerId;


    @ApiModelProperty(value = "数据集类型(0普通数据集 1专用数据集)", required = true)
    private int type;

    @ApiModelProperty(value = "支持的体育类型", required = true)
    private String  sportType;

    @ApiModelProperty(value = "算法或数据集文件", required = true)
    private String file;


    @ApiModelProperty(value = "数据集样例", required = true)
    private String demo;

    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    @ApiModelProperty(value = "mark", required = true)
    private String mark;

    @ApiModelProperty(value = "提交时间", required = true)
    private Date createTime;


}