package com.zlht.pose.management.api.service;


import java.util.Map;

public interface BaseServiceI<T> {



    void putMsg(Map<String, Object> result, int code, String msg);


}
