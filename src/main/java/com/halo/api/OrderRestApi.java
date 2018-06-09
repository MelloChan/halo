//package com.halo.api;
//
//import com.halo.controller.BaseController;
//import com.halo.dto.CartItemDTO;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * @author MelloChan
// * @date 2018/6/2
// */
//@RestController
//@RequestMapping("/api/halo/orders")
//public class OrderRestApi extends BaseController {
//
//    @PostMapping("/{id}")
//    public Map<String, Object> insertOrder(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid,
//                                           CartItemDTO cartItemDTO) {
//        if (id.equals(uid)) {
//
//        }
//    }
//
//    @PostMapping("/{id}/cart")
//    public Map<String, Object> insertOrderByCart(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid) {
//        if (id.equals(uid)) {
//
//        }
//    }
//
//    @GetMapping("/{id}")
//    public Map<String, Object> getOrder(@PathVariable("id") @NotEmpty String id, @RequestAttribute("uid") String uid) {
//        if (id.equals(uid)) {
//
//        }
//    }
//}
