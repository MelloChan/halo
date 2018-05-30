package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.UserInfoService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.constraints.Size;
import java.io.IOException;
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

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid) {
        System.out.println("id:" + id + " uid:" + uid);
        if (id.equals(uid)) {
            return rtnParam(0, ImmutableMap.of("userinfo", userInfoService.getUserProfileInfoByUId(uid)));
        }
        return rtnParam(40006, null);
    }

    @GetMapping("/{id}/coin")
    public Map<String, Object> getCoinById(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid) {
        if (id.equals(uid)) {
            return rtnParam(0, ImmutableMap.of("coin", userInfoService.getHaloCoinByUId(uid)));
        }
        return rtnParam(40006, null);
    }

    @PatchMapping("/{id}/coin")
    public Map<String, Object> updateCoinById(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid, @RequestParam @Size(min = 1, max = 100) Integer number) {
        if (id.equals(uid)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updateCoinByUId(number, uid)));
        }
        return rtnParam(40006, null);
    }

    @PatchMapping("/{id}/updateAvatar")
    public Map<String, Object> updateAvatarById(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid, @RequestPart @NotEmpty Part part) throws IOException {
        if (id.equals(uid)) {
            String avatarUrl = userInfoService.updateAvatarById(part, uid);
            return rtnParam(0, ImmutableMap.of("avatarUrl", avatarUrl));
        }
        return rtnParam(40006, null);
    }


    @PatchMapping("/{id}/updatePwd")
    public Map<String, Object> updatePwdById(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid,
                                             @RequestParam("oldPwd") @Size(min = 8, max = 16) String oldPwd,
                                             @RequestParam("newPwd") @Size(min = 8, max = 16) String newPwd) {

        if (id.equals(uid) && !oldPwd.equals(newPwd)) {
            return rtnParam(0, ImmutableMap.of("msg", userInfoService.updatePwdByUid(newPwd, uid)));
        }
        return rtnParam(40006, null);
    }

    @GetMapping("/{id}/verifyPwd/{pwd}")
    public Map<String,Object>verifyPwd(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid,
                                       @PathVariable("pwd")@Size(min = 8, max = 16) String pwd){
        if(id.equals(uid)){
            return rtnParam(0,ImmutableMap.of("msg",userInfoService.verifyPwd(pwd,uid)));
        }
        return rtnParam(40006, null);
    }
    @PatchMapping("/{id}/updateEmail")
    public Map<String, Object> updateEmailById() {
        return rtnParam(40006, null);
    }

    @PatchMapping("/{id}/updatePhone")
    public Map<String, Object> updatePhoneById() {
        return rtnParam(40006, null);
    }

    @PatchMapping("/{id}/updatePwdPro")
    public Map<String, Object> updatePwdProById() {
        return rtnParam(40006, null);
    }

    @PatchMapping("/{id}/resetPwd")
    public Map<String, Object> resetPwdById() {
        return rtnParam(40006, null);
    }


}
