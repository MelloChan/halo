package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.AuthService;
import com.halo.service.UserInfoService;
import com.halo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 登录 本质就是对token的POST(添加)操作
 * 注销 采用token只需前端将token失效即可
 *
 * @author MelloChan
 * @date 2018/5/3
 */
@RestController
@RequestMapping("/api/halo/auth")
@Validated
public class TokenRestApi extends BaseController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/verifyPhone")
    public Map<String, Object> verifyPhone(@RequestParam("phone") @Size(min = 11, max = 11) String phone) {
        if (authService.verifyPhone(phone)) {
            return rtnParam(0, ImmutableMap.of("msg", "success"));
        } else {
            return rtnParam(40014, null);
        }
    }

    @PostMapping("/loginByPwd")
    public Map<String, Object> loginByPwd(@RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("password") @Size(min = 8, max = 16) String password) throws UnsupportedEncodingException {
        int uid = userInfoService.verifyLoginInfo(phone, password);
        if (uid > 0) {
            String token = TokenUtil.createToken(uid);
            return rtnParam(0, ImmutableMap.of("access_token", token));
        } else {
            return rtnParam(40015, null);
        }
    }

    @GetMapping("/requestSmsCode")
    public Map<String, Object> requestSmsCode(@RequestParam("phone") @Size(min = 11, max = 11) String phone) {
        if (authService.sendSmsCode(phone)) {
            return rtnParam(0, ImmutableMap.of("msg", "success"));
        } else {
            return rtnParam(50002, null);
        }
    }

    @PostMapping("/loginByCode")
    public Map<String, Object> loginByCode(@RequestParam @Size(min = 11, max = 11) String phone, @RequestParam @Size(min = 4, max = 4) String code) throws UnsupportedEncodingException {
        if (authService.verifyCode(phone, code)) {
            int uid = userInfoService.getIdByPhone(phone);
            String token = TokenUtil.createToken(uid);
            return rtnParam(0, ImmutableMap.of("access_token", token));
        } else {
            return rtnParam(40016, null);
        }
    }
}
