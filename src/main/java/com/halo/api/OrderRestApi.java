package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.dto.OrderDetailDTO;
import com.halo.service.AddressService;
import com.halo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/6/2
 */
@RestController
@RequestMapping("/api/halo/orders")
@Validated
public class OrderRestApi extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @GetMapping("/")
    public Map<String, Object> generateOrder(@RequestAttribute("uid") Integer uid) {
        return rtnParam(0, ImmutableMap.of("orderId",orderService.generateOrderId(uid),"address",addressService.getByUId(uid)));
    }
    @PostMapping("/")
    public Map<String,Object>insertOrder(@RequestAttribute("uid") Integer uid,@Valid @RequestBody OrderDetailDTO orderDetailDTO){
        return rtnParam(0,ImmutableMap.of("demo","demo"));
    }
}
