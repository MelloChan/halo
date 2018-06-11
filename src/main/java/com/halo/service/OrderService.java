package com.halo.service;

import com.halo.dto.OrderDetailDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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
    String insertOrderInfo(Integer userId,OrderDetailDTO orderDetailDTO,HttpServletRequest request) throws UnsupportedEncodingException;
}
