package com.halo.dao;

import com.halo.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

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
}
