package com.halo.dao;

import com.halo.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/6
 */
@Mapper
public interface OrderProductDao {
    /**
     * 插入订单商品信息
     */
    void insertOrderProductInfo(OrderProduct orderProduct);

    /**
     * 获取订单商品
     */
    List<OrderProduct> getOrderProduct();

    /**
     * 根据订单id获取订单商品
     */
    List<OrderProduct>getOrderProductByOrderId(@Param("orderId")String orderId);
}
