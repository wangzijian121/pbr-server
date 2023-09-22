package com.zlht.pbr.algorithm.management.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author ziji Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "algorithm", autoResultMap = true)
public class Algorithm {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;


    @ApiModelProperty(value = "算法名", required = true)
    private String name;

    @ApiModelProperty(value = "算法类型(0普通算法 1专用算法)", required = true)
    private Integer type;

    @ApiModelProperty(value = "算法支持体育类型", required = true)
    private Integer sportCategory;

    @ApiModelProperty(value = "模板ID", required = true)
    private Integer templateId;

    @ApiModelProperty(value = "部署方式（0：云端部署 1：本地部署）", required = true)
    private Integer installType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "上传人", required = true)
    private Integer uploader;

    @ApiModelProperty(value = "算法文件", required = true)
    private String file;

    @ApiModelProperty(value = "接口文档")
    private String docs;

    @ApiModelProperty(value = "算法案例")
    private String example;

    @ApiModelProperty(value = "算法调用地址")
    private String url;

    @ApiModelProperty(value = "录入时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}