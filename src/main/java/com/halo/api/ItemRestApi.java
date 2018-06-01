package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/5/31
 */
@RestController
@RequestMapping("/api/halo/items")
@Validated
public class ItemRestApi extends BaseController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public Map<String, Object> getItems(@RequestParam("pageIndex") @Min(1) Integer pageIndex, @RequestParam("pageCount") @Min(1) Integer pageCount) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItems(pageIndex, pageCount)));
    }

    @GetMapping("/{proId}")
    public Map<String, Object> getItemDetailById(@PathVariable("proId") @Min(1) Integer proId) {
        return rtnParam(0, ImmutableMap.of("itemDetail", itemService.getItemDetailByProId(proId)));
    }

    @GetMapping("/{cateId}/cate")
    public Map<String, Object> getItemsByCateId(@PathVariable("cateId") @Min(1) Integer cateId) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItemsByCateId(cateId)));
    }

    @GetMapping("/{cateId}/cate/{typeId}/type")
    public Map<String, Object> getItemsByCateIdAndTypeId(@PathVariable("cateId") @Min(1) Integer cateId,
                                                         @PathVariable("typeId") @Min(1) Integer typeId) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItemsByCateIdAndTypeId(cateId, typeId)));
    }

    @GetMapping("/{cateId}/cate/{brandId}/brand")
    public Map<String, Object> getItemsByCateIdAndBrandId(@PathVariable("cateId") @Min(1) Integer cateId,
                                                          @PathVariable("brandId") @Min(1) Integer brandId) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItemsByCateIdAndBrandId(cateId, brandId)));
    }

    @GetMapping("/{typeId}/type")
    public Map<String, Object> getItemsByTypeId(@PathVariable("typeId") @Min(1) Integer typeId) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItemsByTypeId(typeId)));
    }

    @GetMapping("/{brandId}/brand")
    public Map<String, Object> getItemsByBrandId(@PathVariable("brandId") @Min(1) Integer brandId) {
        return rtnParam(0, ImmutableMap.of("items", itemService.getItemsByBrandId(brandId)));
    }
}
