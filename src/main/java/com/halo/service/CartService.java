package com.halo.service;

import com.auth0.jwt.interfaces.Claim;
import com.halo.dto.CartDTO;
import com.halo.dto.CartItemDTO;
import com.halo.redis.RedisUtil;
import com.halo.util.GsonUtil;
import com.halo.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author MelloChan
 * @date 2018/6/4
 */
@Service
public class CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 加入购物车
     *
     * @param token       登录状态下的token
     * @param cartItemDTO 加入的购物车商品
     * @param request     请求流
     */
    public Cookie insertCartItem(String token, CartItemDTO cartItemDTO,
                                 HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取cookie的购物车信息
        CartDTO cartDTO = getCartDTOByCookie(request);

        // 购物车为空 初始化购物车
        if (null == cartDTO) {
            cartDTO = new CartDTO();
        }

        // 将商品加入购物车
        List<CartItemDTO> carts = addItem(cartDTO,cartItemDTO);

        // 更新购物车商品列表 商品总数 商品总价
        cartDTO.setCarts(carts);
        cartDTO.setTotalNumber(cartDTO.getTotalNumber() + 1);
        cartDTO.setTotalPrice(cartDTO.getTotalPrice() + cartItemDTO.getPrice());

        // 保存购物车信息并且返回相应cookie
        return saveCartAndGetCookie(token,cartDTO);
    }

    /**
     * 获取cookie的购物车信息
     */
    private CartDTO getCartDTOByCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        CartDTO cartDTO = null;
        Cookie[] cookies = request.getCookies();
        // 获取cookie是否有cart信息
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(URLDecoder.decode(cookie.getName(), "utf-8"))) {
                    String cartJson = URLDecoder.decode(cookie.getValue(), "utf-8");
                    cartDTO = (CartDTO) GsonUtil.jsonToObject(cartJson, CartDTO.class);
                    break;
                }
            }
        }
        return cartDTO;
    }

    /**
     *  购物车商品数不少于0 循环遍历是否有相同商品
     */
    private List<CartItemDTO> addItem(CartDTO cartDTO, CartItemDTO cartItemDTO) {
        List<CartItemDTO> carts = cartDTO.getCarts();
        if (carts.size() > 0) {
            for (CartItemDTO cartItem : carts) {
                if (cartItemDTO.getId().equals(cartItem.getId())) {
                    // 增加相同商品数量
                    cartItem.setAmount(cartItem.getAmount() + cartItemDTO.getAmount());
                    break;
                }
            }
        } else {
            // 新商品 直接添加到购物车
            carts.add(cartItemDTO);
        }
        return carts;
    }

    /**
     * 登录状态下存入redis 并清除cookie
     * 未登录状态下 直接设置cookie
     */
    private Cookie saveCartAndGetCookie(String token, CartDTO cartDTO) throws UnsupportedEncodingException {
        Cookie cookie;
        if (token != null) {
            Map<String, Claim> claims = TokenUtil.verifyToken(token);
            int id = claims.get("uid").asInt();
            redisUtil.add(String.valueOf(id), cartDTO);
            cookie = new Cookie("cart", null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
        } else {
            String cartJson = GsonUtil.toJsonString(cartDTO);
            LOGGER.info("cartJson:{}", cartJson);
            cookie = new Cookie("cart", URLEncoder.encode(cartJson, "utf-8"));
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
        }
        return cookie;
    }
    public CartDTO getCart() {

        return null;
    }

    public boolean updateCart() {
        return false;
    }

    public boolean deleteCart() {
        return false;
    }
}
