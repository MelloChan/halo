package com.halo.service.impl;

import com.halo.dao.OrderDao;
import com.halo.dao.OrderProductDao;
import com.halo.dao.OrderShipmentDao;
import com.halo.dto.OrderDetailDTO;
import com.halo.dto.OrderProductDTO;
import com.halo.dto.OrderProductListDTO;
import com.halo.dto.ReceiverInfoDTO;
import com.halo.entity.Order;
import com.halo.entity.OrderProduct;
import com.halo.entity.OrderShipment;
import com.halo.redis.RedisUtil;
import com.halo.service.CartService;
import com.halo.service.ItemService;
import com.halo.service.OrderService;
import com.halo.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author MelloChan
 * @date 2018/6/10
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderShipmentDao orderShipmentDao;
    @Autowired
    private OrderProductDao orderProductDao;

    @Override
    public String generateOrderId(Integer userId) {
        String orderId = DigestUtil.generateSalt();
        redisUtil.add(orderId, 60 * 30L, String.valueOf(userId));
        return orderId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertOrderInfo(Integer userId, OrderDetailDTO orderDetailDTO, HttpServletRequest request) throws UnsupportedEncodingException {
        List<OrderProductDTO> products = orderDetailDTO.getProducts();
        String orderId = orderDetailDTO.getId();
        int total = 0;

        // 保存订单商品信息
        OrderProduct orderProduct;
        for (OrderProductDTO product : products) {
            int proId = product.getProId();
            if (checkStock(proId)) {
                orderProduct = new OrderProduct(proId, orderId, product.getImgUrl(), product.getTitle(),
                        product.getPrice(), product.getTotal(), product.getNumber());
                orderProductDao.insertOrderProductInfo(orderProduct);
                cartService.deleteCart(proId, request);
            } else {
                return product.getTitle();
            }
            total += product.getPrice();
        }

        // 保存配送信息
        ReceiverInfoDTO receiverInfoDTO = orderDetailDTO.getReceiver();
        OrderShipment orderShipment = new OrderShipment(orderId, receiverInfoDTO.getName(), receiverInfoDTO.getPhone(), receiverInfoDTO.getAddress());
        orderShipmentDao.insertOrderShipmentInfo(orderShipment);

        // 保存订单信息
        short payType = orderDetailDTO.getPayType();
        Order order = new Order(orderId, userId, payType, (short) 1, total, total);
        orderDao.insertOrderInfo(order);
        return orderId;
    }

    @Override
    public List<OrderProductListDTO> getOrderProductListByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<OrderDetailDTO> getOrderByOrderId(String orderId) {
        return null;
    }

    private boolean checkStock(int proId) {
        return itemService.updateMinusStockByProId(proId) > 0;
    }

}
