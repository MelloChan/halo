package com.halo.api;

import com.google.common.collect.ImmutableMap;
import com.halo.controller.BaseController;
import com.halo.dto.CartItemDTO;
import com.halo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/6/2
 */
@RestController
@RequestMapping("/api/halo/carts")
@Validated
public class CartRestApi extends BaseController {
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public Map<String, Object> getCart(HttpServletRequest request) throws UnsupportedEncodingException {
        return rtnParam(0, ImmutableMap.of("cart", cartService.getCart(request)));
    }

    @PostMapping("/")
    public Map<String, Object> insertCartItem(@Valid @RequestBody CartItemDTO cartItemDTO,
                                              HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        Cookie cookie = cartService.insertCartItem(cartItemDTO, request);
        response.addCookie(cookie);
        return rtnParam(0, ImmutableMap.of("msg", "success"));
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteCartItem(@PathVariable("id") Integer id, HttpServletRequest request) throws UnsupportedEncodingException {
        cartService.deleteCart(id, request);
        return rtnParam(0, ImmutableMap.of("msg", "success"));
    }

    @PatchMapping("/{id}")
    public Map<String, Object> updateCartItem(@PathVariable("id") Integer id, @RequestParam @Min(1) @Max(100) Integer quantity,
                                              HttpServletRequest request) throws UnsupportedEncodingException {
        cartService.updateCart(id, quantity, request);
        return rtnParam(0, ImmutableMap.of("msg", "success"));
    }
}
