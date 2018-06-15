package com.halo.service;

import com.halo.dto.OrderDetailDTO;
import com.halo.dto.OrderProductListDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/6
 */
public interface OrderService {
    /**
     * 生成订单号
     */
    String generateOrderId(Integer userId);

    /**
     * 插入订单信息
     */
    String insertOrderInfo(Integer userId, OrderDetailDTO orderDetailDTO, HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * 获取用户订单商品列表
     */
    List<OrderProductListDTO> getOrderProductListByUserId(Integer userId);

    /**
     * 通过订单id获取订单详情
     */
    OrderDetailDTO getOrderByOrderId(String orderId);

    /**
     * 根据订单id获取支付状态
     */
    Short getStatusByOrderId(String orderId);
}
