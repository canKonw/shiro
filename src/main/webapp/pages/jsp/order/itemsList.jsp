<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
	<script type="text/javascript">
		//--------------------------------------批量删除商品----------------------------------------------
		function deleteItems(){
			document.itemsForm.action="${pageContext.request.contextPath }/items/deleteItems";
			document.itemsForm.submit();
		}

	</script>
</head>
<body>
当前用户：${userName }
<c:if test="${userName!=null }">
	<a href="${pageContext.request.contextPath }/logout">退出</a>
</c:if>
<form name="itemsForm" action="${pageContext.request.contextPath }/items/queryItem" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td><input type="submit" value="查询"/><input type="button" value="批量删除"  onclick="deleteItems()"/>
	<select>
		<c:forEach items="${itemtype}" var="item">
			<option value="${item.key}">${item.value}</option>
		</c:forEach>
	</select>
</td>

</tr>
</table>
商品列表：33
<table width="100%" border=1>
<tr>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
	<c:forEach items="${itemsList}" var="item">
		<tr>
			<td><input type="checkbox" name="delete_id" value="${item.id}"/></td>
			<td>${item.name}</td>
			<td>${item.price}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.detail}</td>
			<%--<shiro:hasPermission name="item:update">--%>
			<td><a href="${pageContext.request.contextPath }/items/editItems?id=${item.id}">修改</a></td>
			<%--</shiro:hasPermission>--%>
			<td><a href="${pageContext.request.contextPath }/items/viewItems/${item.id}">查询商品信息</a></td>

		</tr>

	</c:forEach>
</table>
</form>
</body>

</html>