package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author SAIKAII
 * @date 2018/6/15
 */
@RestController
@RequestMapping("/api/halo/backstage/firstpage")
public class BackstageFirstPageRestApi extends BaseController {

    @Autowired
    private FirstPageService firstPageService;

    @GetMapping("/")
    public Map<String, Object> getInfo(){
        return rtnParam(0, ImmutableMap.of("info", firstPageService.getFirstPageInfo()));
    }
}
