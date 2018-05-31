package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.ItemService;
import org.hibernate.validator.constraints.NotEmpty;
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
    public Map<String, Object> getItems(@RequestParam("pageIndex")@Min(1) Integer pageIndex, @RequestParam("pageCount")@Min(1) Integer pageCount) {

        return rtnParam(0, ImmutableMap.of("items", itemService.getItems(pageIndex,pageCount)));
    }

    @GetMapping("/{proId}")
    public Map<String, Object> getItemDetailById(@PathVariable("proId") @NotEmpty String proId) {
        return rtnParam(0, ImmutableMap.of("itemDetail",itemService.getItemDetailByProId(proId)));
    }
}
