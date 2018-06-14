package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author SAIKAII
 * @date 2018/6/7
 */
@RestController
@RequestMapping("/api/halo/backstage/admins")
@Validated
public class AdminRestApi extends BaseController{

    @Autowired
    private AdminInfoService adminInfoService;

    @GetMapping("/verityUsername/{username}")
    public Map<String, Object> verityUsername(@PathVariable("username") String username){
        if(adminInfoService.verityByUsername(username)){
            return rtnParam(0, ImmutableMap.of("username",username));
        }else {
            return rtnParam(40014, null);
        }
    }

    @PostMapping("/loginByPwd")
    public Map<String, Object> loginByPwd(@RequestParam("username") String username, @RequestParam("password") @Size(min = 8, max = 16) String password){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if(adminInfoService.verify(username, password)){
            session.setAttribute("admin", username);
            session.setMaxInactiveInterval(5*60);
            return rtnParam(0, ImmutableMap.of("username", username));
        }else {
            return rtnParam(40015, null);
        }
    }
}
