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
@TableName(value = "auth_institution_alg", autoResultMap = true)
public class AuthInstitutionAlg {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Integer id;

    @TableField
    @ApiModelProperty(name = "institution_id", value = "授权机构ID", required = true)
    private Integer institution_id;

    @TableField
    @ApiModelProperty(name = "authType", value = "授权类型", required = true)
    private Integer authType;

    @TableField
    @ApiModelProperty(name = "auth_alg_id", value = "授权算法ID", required = true)
    private Integer auth_alg_id;

    @TableField
    @ApiModelProperty(name = "authAdmin", value = "授权人", required = true)
    private Integer authAdmin;

    @TableField
    @ApiModelProperty(name = "mark", value = "备注", required = false)
    private String mark;

    @TableField
    @ApiModelProperty(name = "authTime", value = "授权时间", required = true)
    private Date authTime;
}