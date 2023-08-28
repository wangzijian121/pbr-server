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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "auth_institution_alg", autoResultMap = true)
public class AuthInstitutionAlg {

    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;


    @ApiModelProperty(name = "institution_id", value = "授权机构ID", required = true)
    private Integer institutionId;


    @ApiModelProperty(name = "authType", value = "授权类型", required = true)
    private Integer authType;


    @ApiModelProperty(name = "auth_alg_id", value = "授权算法ID", required = true)
    private Integer authAlgId;


    @ApiModelProperty(name = "authAdmin", value = "授权人", required = true)
    private Integer authAdmin;


    @ApiModelProperty(name = "mark", value = "备注", required = false)
    private String mark;


    @ApiModelProperty(name = "authTime", value = "授权时间", required = true)
    private Date authTime;
}