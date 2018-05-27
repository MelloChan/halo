package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.dto.UserRegisterInfoDTO;
import com.halo.service.AuthService;
import com.halo.service.UserInfoService;
import com.halo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/api/halo/registers")
@Validated
public class RegisterRestApi extends BaseController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 验证手机号是否注册过
     *
     * @param phone 用户手机号
     */
    @GetMapping("/verifyPhone")
    public Map<String, Object> verifyPhone(@RequestParam("phone") @Size(min = 11, max = 11) String phone) {
        if (!authService.verifyPhone(phone)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        } else {
            return rtnParam(40018, null);
        }
    }

    private static final String TEMP_ID = "318814";

    /**
     * 请求发送短信验证码(注册)
     *
     * @param phone 用户手机号
     */
    @GetMapping("/requestSmsCode")
    public Map<String, Object> requestSmsCode(@RequestParam("phone") @Size(min = 11, max = 11) String phone) {
        if (!authService.verifyPhone(phone)&&authService.sendSmsCode(phone, TEMP_ID)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        } else {
            return rtnParam(50002, null);
        }
    }

    /**
     * 验证短信验证码是否正确
     *
     * @param phone 手机号
     * @param code  短信验证码
     */
    @GetMapping("/verifyCode")
    public Map<String, Object> verifyCode(@RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("code") @Size(min = 4, max = 4) String code) {
        if (authService.verifyCode(phone, code)) {
            return rtnParam(0, ImmutableMap.of("phone", phone));
        } else {
            return rtnParam(40016, null);
        }
    }

    /**
     * 用户注册
     *
     * @param userRegisterInfoDTO 注册信息 手机号+用户名+密码
     * @param bindingResult       绑定结果
     */
    @PostMapping("/registerByPhone")
    public Map<String, Object> register(@Valid @RequestBody UserRegisterInfoDTO userRegisterInfoDTO, BindingResult bindingResult) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return rtnParam(40013, null);
        }
        int uid = userInfoService.insertUserInfo(userRegisterInfoDTO);
        String token = TokenUtil.createToken(uid);
        return rtnParam(0, ImmutableMap.of("access_token", token));
    }
}
