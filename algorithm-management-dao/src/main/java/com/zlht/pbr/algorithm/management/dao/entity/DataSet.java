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
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "data_set", autoResultMap = true)
public class DataSet {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "数据集名", required = true)
    private String name;

    @ApiModelProperty(value = "数据集类型(0普通数据集 1专用数据集)", required = true)
    private Integer type;

    @ApiModelProperty(value = "数据集支持体育类型", required = true)
    private Integer sportCategory;

    @ApiModelProperty(value = "数据集文件", required = true)
    private String file;

    @ApiModelProperty(value = "数据集样例")
    private String demo;

    @ApiModelProperty(value = "部署方式（0：云端部署 1：本地部署）", required = true)
    private Integer installType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "上传人", required = true)
    private Integer uploader;

    @ApiModelProperty(value = "录入时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}