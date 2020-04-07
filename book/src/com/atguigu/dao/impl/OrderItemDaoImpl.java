package com.atguigu.dao.impl;


import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `name`,`count`,`price`,`total_price` totalPrice,`order_id` from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer id) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` from t_order where `user_id` = ?";
        return queryForList(Order.class,sql,id);
    }
}
