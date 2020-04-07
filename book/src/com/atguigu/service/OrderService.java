package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    List<Order> showAllOrders();

    void sendOrder(String orderId);

    List<OrderItem> showOrderDetail(String orderId);

    List<Order> showMyOrders(Integer id);

    void receiveOrder(String orderId);
}
