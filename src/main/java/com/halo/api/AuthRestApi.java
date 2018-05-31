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
@RequestMapping("/api/halo/auths")
@Validated
public class AuthRestApi extends BaseController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 验证手机号码是否注册过
     *
     * @param phone 用户手机号
     */
    @GetMapping("/verifyPhone/{phone}")
    public Map<String, Object> verifyPhone(@PathVariable("phone") @Size(min = 11, max = 11) String phone) {
        if (authService.verifyPhone(phone)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        } else {
            return rtnParam(40014, null);
        }
    }

    /**
     * 通过手机号+密码形式登录
     *
     * @param phone 手机号
     * @param pwd   密码
     */
    @PostMapping("/loginByPwd")
    public Map<String, Object> loginByPwd(@RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("pwd") @Size(min = 8, max = 16) String pwd) throws UnsupportedEncodingException {
        int uid = userInfoService.verifyLoginInfo(phone, pwd);
        if (uid > 0) {
            String token = TokenUtil.createToken(uid);
            return rtnParam(0, ImmutableMap.of("access_token", token));
        } else {
            return rtnParam(40015, null);
        }
    }

    private static final String TEMP_ID = "318993";

    /**
     * 请求发送短信验证码(登录)
     *
     * @param phone 用户手机号
     */
    @GetMapping("/requestSmsCode/{phone}")
    public Map<String, Object> requestSmsCode(@PathVariable("phone") @Size(min = 11, max = 11) String phone) {
        if (authService.verifyPhone(phone) && authService.sendSmsCode(phone, TEMP_ID)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        } else {
            return rtnParam(50002, null);
        }
    }

    /**
     * 通过手机号+短信验证码形式登录
     *
     * @param phone 手机号
     * @param code  输入的验证码 比对缓存中验证码(60s过期)
     */
    @PostMapping("/loginByCode")
    public Map<String, Object> loginByCode(@RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("code") @Size(min = 4, max = 4) String code) throws UnsupportedEncodingException {
        if (authService.verifyCode(phone, code)) {
            int uid = userInfoService.getIdByPhone(phone);
            String token = TokenUtil.createToken(uid);
            return rtnParam(0, ImmutableMap.of("access_token", token));
        }
        return rtnParam(40016, null);
    }


    @PostMapping("/verifyCode")
    public Map<String, Object> verifyCode(@RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("code") @Size(min = 4, max = 4) String code) {
        if (authService.verifyCode(phone, code)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        }
        return rtnParam(40016, null);
    }

    @PostMapping("/resetPwd")
    public Map<String, Object> resetPwd(@RequestParam("phone") @Size(min = 11, max = 11) String phone,
                                        @RequestParam("firstPwd") @Size(min = 8, max = 16) String firstPwd,
                                        @RequestParam("secondPwd") @Size(min = 8, max = 16) String secondPwd) {
        if (firstPwd.equals(secondPwd)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updatePwdByPhone(firstPwd, phone)));
        }
        return rtnParam(40017, null);
    }

}
