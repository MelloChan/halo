package com.halo.dao;

import com.halo.entity.OrderShipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MelloChan
 * @date 2018/6/6
 */
@Mapper
public interface OrderShipmentDao {
    /**
     * 保存配送信息
     */
    void insertOrderShipmentInfo(OrderShipment orderShipment);

    /**
     * 通过订单id获取配送信息
     */
    OrderShipment getOrderShipmentByOrderId(@Param("orderId") String orderId);
}
