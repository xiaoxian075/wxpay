$(function(){
	var _judge = '<c:out value="${_hid}"></c:out>';
	alert(_judge);
	if (_judge==null || _judge=='') {
		alert("submit");
		$("#form").submit();
	}
});