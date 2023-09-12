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
@TableName(value = "wechat", autoResultMap = true)
public class WeChat {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "微信小程序ID", required = true)
    private String appId;

    @ApiModelProperty(value = "微信小程序秘钥", required = true)
    private String appSecret;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String linkCode;

    @ApiModelProperty(value = "小程序名", required = true)
    private String name;

    @ApiModelProperty(value = "机构ID", required = true)
    private Integer institutionId;

    @ApiModelProperty(value = "进度(0已部署，1审核中)", required = true)
    private Integer status;

    @ApiModelProperty(value = "备注", required = false)
    private String mark;

    @ApiModelProperty(value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;


}