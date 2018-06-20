package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.UserInfoService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/page")
    public Map<String, Object> getNumOfPages(@RequestParam("pageCount") Integer pageCount){
        return rtnParam(0, ImmutableMap.of("pages", userInfoService.getNumOfPages(pageCount)));
    }

    @GetMapping("/users")
    public Map<String, Object> getUsers(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageCount") Integer pageCount){
        return rtnParam(0, ImmutableMap.of("users", userInfoService.getUsersProfile(pageIndex, pageCount)));
    }

    @GetMapping("/{uid}")
    public Map<String, Object> getUserProfileByUId(@PathVariable("uid") @Min(1) Integer uid){
        return rtnParam(0, ImmutableMap.of("user", userInfoService.getUserProfileByUId(uid)));
    }

    @DeleteMapping("/{ids}")
    public Map<String, Object> deleteUsersProfile(@PathVariable("ids") @NotEmpty String ids){
        String[] idStr = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String str : idStr){
            str = str.trim();
            idList.add(Integer.valueOf(str));
        }
        userInfoService.deleteUsersProfile(idList);
        return rtnParam(0, ImmutableMap.of("msg", "用户信息删除成功"));
    }
}
