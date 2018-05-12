package com.halo.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 重配置 /error
 * 统一异常处理器
 * @author MelloChan
 * @date 2017/12/5
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BaseErrorController extends BaseController implements ErrorController{
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping
    @ResponseBody
    public Map<String, Object> doHandleError(){
        return rtnParam(40003,null);
    }
}
