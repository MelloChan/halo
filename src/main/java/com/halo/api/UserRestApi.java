package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.AuthService;
import com.halo.service.UserInfoService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Part;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;


/**
 * @author MelloChan
 * @date 2018/5/10
 */
@RestController
@RequestMapping("/api/halo/users")
@Validated
public class UserRestApi extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthService authService;

    /**
     * 获取单个用户信息
     */
    @GetMapping("/")
    public Map<String, Object> getById(@RequestAttribute("uid") String uid) {
        return rtnParam(0, ImmutableMap.of("userinfo", userInfoService.getUserProfileInfoByUId(uid)));
    }

    /**
     * 获取用户哈币信息
     */
    @GetMapping("/coin")
    public Map<String, Object> getCoinById(@RequestAttribute("uid") String uid) {
        return rtnParam(0, ImmutableMap.of("coin", userInfoService.getHaloCoinByUId(uid)));
    }

    /**
     * 更新哈币数量
     *
     * @param number 用户购买成功的哈币数
     */
    @PatchMapping("/coin")
    public Map<String, Object> updateCoinById(@RequestAttribute("uid") String uid, @RequestParam @Min(1) @Max(100) Integer number) {
        return rtnParam(0, ImmutableMap.of("msg", userInfoService.updateCoinByUId(number, uid)));
    }

    /**
     * 更新用户头像
     *
     * @param part 头像数据
     */
    @PatchMapping("/updateAvatar")
    public Map<String, Object> updateAvatarById(@RequestAttribute("uid") String uid, @RequestPart("imgFile") @NotEmpty Part part) throws IOException {
        String avatarUrl = userInfoService.updateAvatarByUId(part, uid);
        return rtnParam(0, ImmutableMap.of("avatarUrl", avatarUrl));
    }

    /**
     * 更新密码 需要先验证手机->验证手机验证码
     */
    @PatchMapping("/updatePwd")
    public Map<String, Object> updatePwdById(@RequestAttribute("uid") String uid, @RequestParam("oldPwd") @Size(min = 8, max = 16) String oldPwd,
                                             @RequestParam("newPwd") @Size(min = 8, max = 16) String newPwd) {
        if (!oldPwd.equals(newPwd)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updatePwdByUId(newPwd, uid)));
        }
        return rtnParam(40006, null);
    }

    /**
     * 个人中心针对更新邮箱或手机时的密码验证
     */
    @GetMapping("/verifyPwd/{pwd}")
    public Map<String, Object> verifyPwd(@RequestAttribute("uid") String uid,
                                         @PathVariable("pwd") @Size(min = 8, max = 16) String pwd) {
        return rtnParam(0, ImmutableMap.of("msg", userInfoService.verifyPwd(pwd, uid)));
    }

    /**
     * 验证邮箱验证码
     */
    @GetMapping("/requestEmailVerify/{email}")
    public Map<String, Object> requestEmailVerify(@PathVariable("email") @Email String email) throws GeneralSecurityException, MessagingException {
        authService.sendEmail(email);
        return rtnParam(0, ImmutableMap.of("email", email));
    }

    /**
     * 更新邮箱
     */
    @PatchMapping("/updateEmail")
    public Map<String, Object> updateEmailById(@RequestAttribute("uid") String uid,
                                               @RequestParam("email") @Email String email, @RequestParam("code") @Size(min = 6, max = 6) String code) {
        if (authService.verifyEmailCode(email, code)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updateEmailByUId(email, uid)));
        }
        return rtnParam(40006, null);
    }

    /**
     * 更新手机号
     * 验证密码->验证旧手机->验证新手机
     */
    @PatchMapping("/updatePhone")
    public Map<String, Object> updatePhoneById(@RequestAttribute("uid") String uid,
                                               @RequestParam("phone") @Size(min = 11, max = 11) String phone, @RequestParam("code") @Size(min = 6, max = 6) String code) {
        if (authService.verifyCode(phone, code)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updatePhoneByUId(phone, uid)));
        }
        return rtnParam(40006, null);
    }
}
