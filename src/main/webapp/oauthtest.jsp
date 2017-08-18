<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>支付测试</title>
	
	<script type="text/javascript">
		$(function(){
			var _judge = '<c:out value="${_hid}"></c:out>';
			alert(_judge);
			if (_judge==null || _judge=='') {
				alert("submit");
				$("#form").submit();
			}
		});
		/*
		https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9d9fa7e8979ecf7c&redirect_uri=http%3A%2F%2Ftest-user.ipaye.cn%2Fwxpay%2Fpay.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect
				*/
	</script>
</head>
<body>
	<h1>这是支付测试页面</h1>
	<h1><a id="_mya" href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9d9fa7e8979ecf7c&redirect_uri=http%3A%2F%2Ftest-user.ipaye.cn%2Fwxpay%2Fpay.jsp&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect">点击测试</a></h1>
</body>
</html>
