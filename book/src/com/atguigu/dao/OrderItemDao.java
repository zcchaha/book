package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    List<Order> queryOrdersByUserId(Integer id);
}
