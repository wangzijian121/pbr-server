package com.zlht.pbr.algorithm.management.enums;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Optional;

/**
 * status enum
 *
 * @author zi jian Wang
 */
public enum Status {

    /**
     * 成功
     */
    SUCCESS(200, "success", "成功"),
    /**
     * 用户
     */
    USER_NO_OPERATION_PERM(10001, "USER NO OPERATION PERM", "用户没有权限"),
    /**
     * 授权用户错误
     */
    AUTHORIZED_USER_ERROR(10002, "AUTHORIZED USER ERROR", "授权用户错误"),


    /**
     * 用户名已存在
     */
    USER_NAME_EXIST(10003, "user name already exists", "用户名已存在"),
    /**
     * 用户名不能为空
     */
    USER_NAME_NULL(10004, "user name is null", "用户名不能为空"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(10042, "login success", "登录成功"),
    /**
     * 用户登录失败
     */
    USER_LOGIN_FAILURE(10043, "user login failure", "用户登录失败"),
    /**
     * 退出错误
     */
    SIGN_OUT_ERROR(10123, "sign out error", "退出错误"),
    /**
     * IP地址不能为空
     */
    IP_IS_EMPTY(10125, "ip is empty", "IP地址不能为空"),

    /**
     * 名称不能为空
     */
    NAME_NULL(10134, "name must be not null", "名称不能为空"),
    /**
     * 资源不存在
     */
    RESOURCE_NOT_EXIST(20004, "resource not exist", "资源不存在"),
    /**
     * 资源已存在
     */
    RESOURCE_EXIST(20005, "resource already exists", "资源已存在"),

    /**
     * 请求参数无效错误
     */
    REQUEST_PARAMS_NOT_VALID_ERROR(20006, "AUTHORIZED USER ERROR", "请求参数无效错误");

    private final int code;
    private final String enMsg;
    private final String zhMsg;

    Status(int code, String enMsg, String zhMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    /**
     * 获取Code
     *
     * @return
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取Msg
     *
     * @return
     */
    public String getMsg() {
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
            return this.zhMsg;
        } else {
            return this.enMsg;
        }
    }

    /**
     * Retrieve Status enum entity by status code.
     */
    public static Optional<Status> findStatusBy(int code) {
        for (Status status : Status.values()) {
            if (code == status.getCode()) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
