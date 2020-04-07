<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单明细</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>



</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单明细</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/pages/common/manager_menu.jsp"%>


	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>

				<c:forEach items="${requestScope.orderItemList}" var="orderItem">
			<tr>
					<td>${orderItem.name}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.totalPrice}</td>
			</tr>
				</c:forEach>

		</table>

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${requestScope.count}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${requestScope.totalPrice}</span>元</span>
			</div>


	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>