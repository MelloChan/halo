package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.BackstageProductManageService;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author SAIKAII
 * @date 2018/6/12
 */
@RestController
@RequestMapping("/api/halo/backstage/productmanage")
@Validated
public class BackstageProductManageRestApi extends BaseController {

    @Autowired
    private BackstageProductManageService backstageProductManageService;

    @GetMapping("/page")
    public Map<String, Object> getNumOfPages(@Param("pageCount") @Min(1) Integer pageCount) {
        return rtnParam(0, ImmutableMap.of("pages", backstageProductManageService.getNumOfPage(pageCount)));
    }

    @GetMapping("/")
    public Map<String, Object> getItems(@RequestParam("pageIndex") @Min(1) Integer pageIndex,
                                        @RequestParam("pageCount") @Min(1) Integer pageCount) {
        return rtnParam(0, ImmutableMap.of("items", backstageProductManageService.getItemsForBackstage(pageIndex, pageCount)));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteItem(@PathVariable("id") @Min(1) Integer id) {
        backstageProductManageService.deleteProduct(id);
        return rtnParam(0, ImmutableMap.of("msg", "商品删除成功"));
    }

    @DeleteMapping("/{ids}/multi")
    public Map<String, Object> deleteMultiItems(@PathVariable("ids") String ids) {
        String[] idStr = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : idStr) {
            s = s.trim();
            idList.add(Integer.valueOf(s));
        }
        backstageProductManageService.deleteMultiProducts(idList);
        return rtnParam(0, ImmutableMap.of("msg", "多个商品删除成功"));
    }

    @PatchMapping("/productInfoByPId")
    public Map<String, Object> updateProductInfoByPId(@RequestParam("id") @Min(1) Integer id,
                                                      @RequestParam("name") @NotEmpty String name,
                                                      @RequestParam(value = "num", defaultValue = "0") Integer num,
                                                      @RequestParam("price") @Min(0) Integer price) {
        backstageProductManageService.updateProductInfoByPid(id, name, num, price);
        return rtnParam(0, ImmutableMap.of("msg", "更新成功"));
    }

    @GetMapping("/typeAndName")
    public Map<String, Object> getItemsInfoByTypeAndName(@RequestParam("type") @NotEmpty String type,
                                                         @RequestParam("name") @NotEmpty String name,
                                                         @RequestParam("pageIndex") @Min(1) Integer pageIndex,
                                                         @RequestParam("pageCount") @Min(1) Integer pageCount) {
        return rtnParam(0, ImmutableMap.of("items", backstageProductManageService.getItemsForBackstageByTypeAndName(type, name, pageIndex, pageCount)));
    }

}
