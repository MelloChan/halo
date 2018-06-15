package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author SAIKAII
 * @date 2018/6/14
 */
@RestController
@RequestMapping("/api/halo/backstage/usermanage")
@Validated
public class BackstageUserManageRestApi extends BaseController{
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUsers")
    public Map<String, Object> getUsers(){
        return rtnParam(0, ImmutableMap.of("users", userInfoService.getUsersProfile()));
    }

    @GetMapping("/{uid}")
    public Map<String, Object> getUserProfileByUId(@PathVariable("uid") Integer uid){
        return rtnParam(0, ImmutableMap.of("user", userInfoService.getUserProfileByUId(uid)));
    }

    @DeleteMapping("/{ids}")
    public Map<String, Object> deleteUsersProfile(@PathVariable("ids") @NotNull String ids){
        String[] idStr = ids.split(",");
        ArrayList<Integer> idList = new ArrayList<>();
        for(String str : idStr){
            str = str.trim();
            idList.add(Integer.valueOf(str));
        }
        userInfoService.deleteUsersProfile(idList);
        return rtnParam(0, ImmutableMap.of("msg", "用户信息删除成功"));
    }
}
