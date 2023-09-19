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
 * 微信上报数据类
 *
 * @author zijian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_report_data", autoResultMap = true)
public class WxReportData {
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "小程序链接码")
    private String linkCode;

    @ApiModelProperty(value = "今日使用人数")
    private Integer userCountToday;

    @ApiModelProperty(value = "今日用户使用时长总和(秒)")
    private Integer userUsageTimeToday;

    @ApiModelProperty(value = "算法使用次数")
    private Integer AlgorithmCountToday;

    @ApiModelProperty(value = "创建时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}