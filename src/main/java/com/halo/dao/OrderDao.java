package com.halo.dao;

import com.halo.dto.OrderListDTO;
import com.halo.dto.OrderProductListDTO;
import com.halo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据用户id获取用户的所有订单部分信息
     */
    List<Order> getByUId(@Param("userId") Integer userId);

    /**
     * 根据订单id获取订单状态
     */
    Short getStatusByOrderId(@Param("orderId")String orderId);

    /**
     * 根据订单id获取订单支付类型
     */
    Short getPayTypeByOrderId(@Param("orderId")String orderId);
    /**
     * 返回所有订单详情
     */
    List<OrderListDTO> getOrders(@Param("pageIndex") Integer pageIndex,
                                 @Param("pageCount") Integer pageCount);

    /**
     * 通过状态筛选订单
     */
    List<OrderListDTO> getOrdersByStatus(@Param("status") Short status,
                                                @Param("pageIndex") Integer pageIndex,
                                                @Param("pageCount") Integer pageCount);

    /**
     * 通过订单id更改订单状态
     */
    void updateOrderStatusById(@Param("id") String id, @Param("status") Short status);

    /**
     * 获得所有订单数
     */
    Integer getNumOfOrder();

    /**
     * 获得未发货订单数
     */
    Integer getNumOfNoHandledOrder();

    /**
     * 获得成交总额
     */
    Integer getTotalTurnOver();

    /**
     * 获取一个月内魅族销售量
     */
    Integer getMeiZuSales();

    /**
     * 获取一个月内魅蓝销售量
     */
    Integer getMeiLanSales();

}
