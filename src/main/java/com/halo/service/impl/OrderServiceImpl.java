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
import java.util.ArrayList;
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
        List<OrderProductListDTO> orderProductListDTOS = new ArrayList<>();
        OrderProductListDTO orderProductListDTO;
        OrderProductDTO orderProductDTO;
        List<Order> orders = orderDao.getByUId(userId);
        List<OrderProduct> orderProducts;
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderProductListDTO = new OrderProductListDTO();
            orderProductListDTO.setId(order.getId());
            orderProductListDTO.setGmtUpdated(order.getGmtUpdated());
            orderProductListDTO.setPrice(order.getPayAmount());
            orderProductListDTO.setStatus(order.getOrderStatus());
            orderProducts = orderProductDao.getOrderProductByOrderId(order.getId());
            for (OrderProduct orderProduct : orderProducts) {
                orderProductDTO = new OrderProductDTO();
                orderProductDTO.setTitle(orderProduct.getTitle());
                orderProductDTO.setProId(orderProduct.getProId());
                orderProductDTO.setPrice(orderProduct.getPrice());
                orderProductDTO.setNumber(orderProduct.getNumber());
                orderProductDTO.setTotal(orderProduct.getTotalFee());
                orderProductDTOS.add(orderProductDTO);
            }
            orderProductListDTO.setProducts(orderProductDTOS);
            orderProductListDTOS.add(orderProductListDTO);
        }
        return orderProductListDTOS;
    }

    @Override
    public OrderDetailDTO getOrderByOrderId(String orderId) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderId);

        // 设置配送地址
        OrderShipment orderShipment = orderShipmentDao.getOrderShipmentByOrderId(orderId);
        ReceiverInfoDTO receiverInfoDTO = new ReceiverInfoDTO();
        receiverInfoDTO.setName(orderShipment.getReceiverName());
        receiverInfoDTO.setPhone(orderShipment.getReceiverPhone());
        receiverInfoDTO.setAddress(orderShipment.getReceiverAddress());
        orderDetailDTO.setReceiver(receiverInfoDTO);

        // 配置订单商品信息
        List<OrderProduct> orderProducts = orderProductDao.getOrderProductByOrderId(orderId);
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        OrderProductDTO orderProductDTO;
        for (OrderProduct product : orderProducts) {
            orderProductDTO = new OrderProductDTO();
            orderProductDTO.setImgUrl(product.getImage());
            orderProductDTO.setNumber(product.getNumber());
            orderProductDTO.setPrice(product.getPrice());
            orderProductDTO.setProId(product.getProId());
            orderProductDTO.setTitle(product.getTitle());
            orderProductDTOS.add(orderProductDTO);
        }
        orderDetailDTO.setProducts(orderProductDTOS);

        return orderDetailDTO;
    }

    @Override
    public Short getStatusByOrderId(String orderId) {
        return orderDao.getStatusByOrderId(orderId);
    }

    private boolean checkStock(int proId) {
        return itemService.updateMinusStockByProId(proId) > 0;
    }

}
