package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.UserInfoService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
