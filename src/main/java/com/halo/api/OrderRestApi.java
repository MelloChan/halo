package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.service.AddressService;
import com.halo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/6/2
 */
@RestController
@RequestMapping("/api/halo/orders")
public class OrderRestApi extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @PostMapping("/")
    public Map<String, Object> generateOrder(@RequestAttribute("uId") Integer uId) {
        return rtnParam(0, ImmutableMap.of("orderId",orderService.generateOrderId(uId),"address",addressService.getByUId(uId)));
    }
}
