package com.zlht.pose.management.api.service.impl;


import com.zlht.pose.management.api.service.BaseServiceI;
import com.zlht.pose.management.api.utils.Result;

import java.util.List;

public class BaseServiceImpl<T> implements BaseServiceI<T> {

    private final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MSG = "success";

    @Override
    public Result<T> success(List<T> data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        result.setData(data);
        return result;
    }


    @Override
    public Result<T> faild(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
