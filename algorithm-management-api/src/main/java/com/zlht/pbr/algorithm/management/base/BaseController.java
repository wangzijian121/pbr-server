package com.zlht.pbr.algorithm.management.base;

import com.zlht.pbr.algorithm.management.enums.Constants;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public class BaseController {
    private static final Logger logger = LogManager.getLogger(BaseController.class);
    private final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MSG = "success";
    private final static String PAGE_NUMBER = "currentPage";
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
        String unKnown = "unKnown";
        String realIp = request.getHeader("X-Real-IP");

        if (StringUtils.isNotEmpty(realIp) && !unKnown.equalsIgnoreCase(realIp)) {
            return realIp;
        }
        String forwardedIp = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(forwardedIp) && !unKnown.equalsIgnoreCase(forwardedIp)) {
            int index = forwardedIp.indexOf(",");
            if (index != -1) {
                return forwardedIp.substring(0, index);
            } else {
                return forwardedIp;
            }
        }
        logger.info("请求方信息：");
        logger.info("realIp:" + realIp);
        logger.info("forwardedIp:" + forwardedIp);
        logger.info("RemoteAddr:" + request.getRemoteAddr());
        return request.getRemoteAddr();
    }
}