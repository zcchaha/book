package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    List<Order> queryOrders();

    int changeOrderStatus(String orderId,Integer i);
}
