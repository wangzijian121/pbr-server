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
@TableName(value = "sharing", autoResultMap = true)
public class Sharing {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "机构管理员ID", required = true)
    private String userId;

    @ApiModelProperty(value = "分成金额", required = true)
    private Double money;

    @ApiModelProperty(value = "付款截图", required = true)
    private String screenshotOfPayment;

    @ApiModelProperty(value = "状态(0:未付款，1：已付款)", required = true)
    private Integer status;

    @ApiModelProperty(value = "备注", required = false)
    private String mark;

    @ApiModelProperty(value = "添加时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}