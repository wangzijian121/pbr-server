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
@TableName(value = "charge", autoResultMap = true)
public class Charge {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "收费项类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ", required = true)
    private Integer type;

    @ApiModelProperty(value = "付款机构ID", required = true)
    private Integer institutionId;

    @ApiModelProperty(value = "确认人ID", required = true)
    private Integer confirmPeople;

    @ApiModelProperty(value = "收款时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date chargeTime;

    @ApiModelProperty(value = "确认时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date confirmTime;

    @ApiModelProperty(value = "到账状态（0：已到账，1：未到账）", required = true)
    private Integer status;

    @ApiModelProperty(value = "备注", required = false)
    private String mark;

    @ApiModelProperty(value = "录入时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;

}