package com.zlht.pose.management.api.controller;

import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.utils.Result;

import java.text.MessageFormat;

public class BaseController {

    private  final static  String PAGE_NUMBER="pageNum";
    private  final static  String PAGE_SIZE="pageSize";

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
}
