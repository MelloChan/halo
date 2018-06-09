package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@RestController
@RequestMapping("/api/halo/categorys")
public class CategoryRestApi extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public Map<String,Object>getCate(){
        return (ImmutableMap.of("errorCode",0,"data",categoryService.getCate()));
    }
    @GetMapping("/{cateId}")
    public Map<String, Object> getCateByCateId(@PathVariable("cateId") @Min(1) Integer cateId) {
        return rtnParam(0,ImmutableMap.of("errorCode",0,"data",categoryService.getCateByCateId(cateId)));
    }

}
