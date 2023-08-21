
package com.zlht.pbr.algorithm.management.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * session
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("session")
public class Session {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    /**
     * user id
     */
    private Integer userId;

    private String ip;

    /**
     * last login time
     */
    private Date lastLoginTime;


}
