package com.halo.dao;

import com.halo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MelloChan
 * @date 2018/6/6
 */
@Mapper
public interface OrderDao {
    /**
     * 插入订单信息
     */
    void insertOrderInfo(Order order);
}
