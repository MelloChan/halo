package com.halo.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 基础控制器 返回json数据
 * @author MelloChan
 * @date 2017/7/1
 */
@Component
public abstract class BaseController {
    @Autowired
    private ImmutableMap<String,String>errorCodeMap;
    /**
     * 接口数据返回
     *
     * @param errorCode 转态码
     * @param data      数据
     * @return 参数数据
     */
    protected Map<String, Object> rtnParam(Integer errorCode, Object data) {
        //正常业务返回
        if (0 == errorCode) {
            return ImmutableMap.of("errorCode", errorCode, "data", (data == null) ? new Object() : data);
        } else {
            return ImmutableMap.of("errorCode", errorCode, "msg",errorCodeMap.get(String.valueOf(errorCode)));
        }
    }
}
