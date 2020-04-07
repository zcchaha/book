package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderServlet extends  BaseServlet {
    private OrderService orderService = new OrderServiceImpl();


    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }


        protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        List<Order> orders = orderService.showMyOrders(user.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }


        protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItemList = orderService.showOrderDetail(orderId);
        int count = 0;
        BigDecimal totalPrice = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            count += orderItem.getCount();
            totalPrice= totalPrice.add(orderItem.getTotalPrice());
        }
        req.setAttribute("count", count);
        req.setAttribute("totalPrice", totalPrice);
        req.setAttribute("orderItemList",orderItemList);
        req.getRequestDispatcher("/pages/manager/order_detail.jsp").forward(req,resp);
    }


        protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }


        protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

        protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User)req.getSession().getAttribute("user");

        if (loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        } else {
            Integer userId = loginUser.getId();
            String orderId = orderService.createOrder(cart, userId);
            req.getSession().setAttribute("orderId", orderId);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }
    }
}
