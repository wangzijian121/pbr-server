package com.zlht.pose.management.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.service.BaseServiceI;
import com.zlht.pose.management.api.utils.Result;

import java.util.List;

public class BaseServiceImpl  implements BaseServiceI {


    @Override
    public Result success(List data) {
        return null;
    }


    @Override
    public Result faild(int code, String msg) {
        return null;
    }
}
