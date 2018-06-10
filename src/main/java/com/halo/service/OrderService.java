package com.halo.service;

/**
 * @author MelloChan
 * @date 2018/6/6
 */
public interface OrderService {
    /**
     * 生成订单号
     */
    String generateOrderId(Integer userId);


}
