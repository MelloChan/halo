package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.BackstageProductManageService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author SAIKAII
 * @date 2018/6/12
 */
@RestController
@RequestMapping("/api/halo/backstage/productmanage")
@Validated
public class BackstageProductManageRestApi extends BaseController{

    @Autowired
    BackstageProductManageService backstageProductManageService;

    @GetMapping("/")
    public Map<String, Object> getItems(@RequestParam("pageIndex") @Min(1) Integer pageIndex,
                                        @RequestParam("pageCount") @Min(1) Integer pageCount){
        return rtnParam(0, ImmutableMap.of("items", backstageProductManageService.getItemsForBackstage(pageIndex, pageCount)));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteItem(@PathVariable("id") @Min(1) Integer id){
        backstageProductManageService.deleteProduct(id);
        return rtnParam(0, ImmutableMap.of("msg", new String("商品删除成功")));
    }

    @DeleteMapping("/{ids}/multi")
    public Map<String, Object> deleteMultiItems(@PathVariable("ids")String ids){
        String[] idStr=ids.split(",");
        ArrayList<Integer> idList = new ArrayList<>();
        for(String s : idStr){
            idList.add(Integer.valueOf(s));
        }
        backstageProductManageService.deleteMultiProducts(idList);
        return rtnParam(0, ImmutableMap.of("msg", new String("多个商品删除成功")));
    }

    @PatchMapping("/name")
    public Map<String, Object> updateNameById(@RequestParam("id") @Min(1) Integer id,
                                              @RequestParam("name") @NotEmpty String name,
                                              @RequestParam("num") Integer num,
                                              @RequestParam("price") @Min(0) Integer price){
        backstageProductManageService.updateProductInfoByPid(id, name, num, price);
        return rtnParam(0, ImmutableMap.of("msg", new String("更新成功")));
    }

    @GetMapping("/typeAndName")
    public Map<String, Object> getItemsInfoByTypeAndName(@RequestParam("type") @NotNull String type,
                                                  @RequestParam("name") @NotNull String name,
                                                  @RequestParam("pageIndex") @Min(1) Integer pageIndex,
                                                  @RequestParam("pageCount") @Min(1) Integer pageCount){
        return rtnParam(0, ImmutableMap.of("items", backstageProductManageService.getItemsForBackstageByTypeAndName(type, name, pageIndex, pageCount)));
    }

}
