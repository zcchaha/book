<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>



</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				$("a.deleteItem").click(function () {
					return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"吗？")
				});

				$("#clearCart").click(function () {
					return confirm("你确定要清空购物车吗？")
				});

				$(".updateCount").change(function () {
					var name = $(this).parent().parent().find("td:first").text();
					var id = $(this).attr("bookId");
					var count = this.value;
					if(confirm("你确定要将【"+name+"】商品数量修改为"+count+"吗？")){
						// location.href="http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;;
						$.getJSON("${basePath}cartServlet","action=ajaxUpdateCount&id=" + id + "&count=" + count,function(data){
							console.log(data);
							// 显示修改后购物车的总数量
							$("span.b_count").text( data.cartTotalCount );
							// 显示修改后购物车的总金额
							$("span.b_price").text( data.cartTotalPrice );
							// 显示修改项的总金额
							$tdObj.text( data.updateItemTotalPrice );
						});
					} else {
						this.value = this.defaultValue;
					}
				});
			});
		</script>


	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>


			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">购物车还么有商品，快去添加吧 ！！！</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td><input class="updateCount" style="width: 80px;" bookId="${entry.value.id}" type="text" value="${entry.value.count}"></td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


			

			
		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>