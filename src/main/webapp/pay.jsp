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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>pay</title>
</head>
<body>
	<form id = "form" action="getwxui.do">
		<input id="code" name="code" type="hidden" value="<%=request.getParameter("code")%>"></input><br>
		<input id="state" name="state" type="hidden" value="<%=request.getParameter("state")%>"></input><br>
		<input type="submit">
	</form>
	<script type="text/javascript">
		$("#form").submit();
	</script>
</body>
</html>
