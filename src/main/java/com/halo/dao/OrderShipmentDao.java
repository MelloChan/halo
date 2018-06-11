package com.halo.dao;

import com.halo.entity.OrderShipment;
import org.apache.ibatis.annotations.Mapper;

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
}
