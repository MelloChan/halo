package com.halo.api;

import com.halo.controller.BaseController;
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
    @GetMapping("/{cateId}")
    public Map<String, Object> getCateByCateId(@PathVariable("cateId") @Min(1) Integer cateId) {

    }

    @GetMapping("/{cateId}/type/{typeId}")
    public Map<String, Object> getCateByTypeId(@PathVariable("cateId") @Min(1) Integer cateId, @PathVariable("typeId") @Min(1) Integer typeId) {

    }
}
