<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>


    <%--<c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
    </c:if>--%>

    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=showMyOrders">我的订单</a>
    <a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>