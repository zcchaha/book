package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();




    protected void ajaxAddItem(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求参数商品编号
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 2、调用BookService.queryBookById( id ):Book对象;
        Book book = bookService.queryBookById(id);
        // 3、把Book对象转换成为CartItem实例
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1,
                book.getPrice(), book.getPrice());
        // 4、创建（获取）Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        // 5、调用cart.addItem()添加商品项
        cart.addItem(cartItem);
        System.out.println(cart);
        // 保存最后 一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());

        // 需要返回购物车总的商品数量 和 最后一个添加的商品名称
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("totalCount", cart.getTotalCount());
        result.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String resultJsonString = gson.toJson(result);
        response.getWriter().write(resultJsonString);
//        response.sendRedirect(request.getHeader("Referer"));
    }



   /* protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }*/

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart  = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart  = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void ajaxUpdateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart  = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            BigDecimal updateItemTotalPrice= cart.updateCount(id, count);

            // 返回 三个信息，给客户端。
            /**
             * 修改的商品总金额
             * 购物车总数量
             * 购物车总金额
             */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("updateItemTotalPrice", updateItemTotalPrice);
            map.put("cartTotalCount", cart.getTotalCount());
            map.put("cartTotalPrice", cart.getTotalPrice());

            Gson gson = new Gson();
            String resultJsonString = gson.toJson(map);
            resp.getWriter().write(resultJsonString);
        }

    }


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart  = (Cart)req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
