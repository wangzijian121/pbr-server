package com.zlht.pose.management.api.controller;

import com.zlht.pose.management.api.enums.Constants;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.utils.Result;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

public class BaseController {

    private final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MSG = "success";
    private final static String PAGE_NUMBER = "pageNum";
    private final static String PAGE_SIZE = "pageSize";

    public Result checkPageParams(int pageNo, int pageSize) {
        Result result = new Result();
        Status resultEnum = Status.SUCCESS;
        String msg = Status.SUCCESS.getMsg();
        if (pageNo <= 0) {
            resultEnum = Status.REQUEST_PARAMS_NOT_VALID_ERROR;
            msg = MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), PAGE_NUMBER);
        } else if (pageSize <= 0) {
            resultEnum = Status.REQUEST_PARAMS_NOT_VALID_ERROR;
            msg = MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), PAGE_SIZE);
        }
        result.setCode(resultEnum.getCode());
        result.setMsg(msg);
        return result;
    }

    public Result returnDataList(Map<String, Object> result) {
        Status status = (Status) result.get(Constants.STATUS);
        if (status == Status.SUCCESS) {
            String msg = result.get("msg").toString();
            Object datalist = result.get(Constants.DATA_LIST);
            return success(msg, datalist);
        } else {
            Integer code = 400;
            String msg = (String) result.get(Constants.MSG);
            return error(code, msg);
        }
    }

    public Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    public Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");

        if (StringUtils.isNotEmpty(clientIp) && !clientIp.equalsIgnoreCase("unKnown")) {
            int index = clientIp.indexOf(",");
            if (index != -1) {
                return clientIp.substring(0, index);
            } else {
                return clientIp;
            }
        }

        clientIp = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(clientIp) && !clientIp.equalsIgnoreCase("unKnown")) {
            return clientIp;
        }

        return request.getRemoteAddr();
    }
}
