
package com.zlht.pbr.algorithm.management.utils;

import com.zlht.pbr.algorithm.management.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * result
 *
 * @param <T> T
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * status
     */
    private Integer code;

    /**
     * message
     */
    private String msg;

    /**
     * data
     */
    private T data;

    public Boolean checkResult() {
        return this.code == Status.SUCCESS.getCode();
    }
}
