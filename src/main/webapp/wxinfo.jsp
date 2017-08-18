<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
	<base href="<%=basePath%>">
	<script type="text/javascript" src="./js/jquery.js"></script>
	<!-- <script type="text/javascript" src="./js/pay.js"></script> -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>获取用户信息</title>
</head>
<body>
	<h1>获取用户信息</h1>

	<table>
		<tr>
			<th>openid</th>
			<th><c:out value="${openid}"></c:out></th>
		</tr>
		<tr>
			<th>nickname</th>
			<th><c:out value="${nickname}"></c:out></th>
		</tr>
		<tr>
			<th>sex</th>
			<th><c:out value="${sex}"></c:out></th>
		</tr>
		<tr>
			<th>province</th>
			<th><c:out value="${province}"></c:out></th>
		</tr>
		<tr>
			<th>city</th>
			<th><c:out value="${city}"></c:out></th>
		</tr>
		<tr>
			<th>country</th>
			<th><c:out value="${country}"></c:out></th>
		</tr>
		<tr>
			<th>headimgurl</th>
			<th><c:out value="${headimgurl}"></c:out></th>
		</tr>
		<tr>
			<th>privilege</th>
			<th><c:out value="${privilege}"></c:out></th>
		</tr>
		<tr>
			<th>unionid</th>
			<th><c:out value="${unionid}"></c:out></th>
		</tr>
	</table>
</body>
</html>
