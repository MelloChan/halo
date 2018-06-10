package com.halo.service.impl;

import com.halo.redis.RedisUtil;
import com.halo.service.OrderService;
import com.halo.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String generateOrderId(Integer userId) {
        String orderId= DigestUtil.generateSalt();
        redisUtil.add(orderId,60*30L,String.valueOf(userId));
        return orderId;
    }
}
