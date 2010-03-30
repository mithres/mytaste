<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Room Player</title>

<script type="text/javascript"
	src="<web.page:path/>/lps/includes/embed-compressed.js "></script>
<script>
	function initPlayer() {
		var training = lz.embed.lzapp;
		var roomId = '<s:property value="roomId"/>';
		var sid = '<s:property value="sid"/>';
		var nodeUrl = '<s:property value="nodeUrl"/>';
		var callMethod = "init('" + roomId + "','" + sid + "','" + nodeUrl + "')";
		training.callMethod(callMethod);
	}
</script>
</head>

<body onload="initPlayer();">
<!-- url : '<web.page:path/>/vod/vodplayer.lzx.swf8.swf', -->
<script>
	lz.embed.swf( {
		url : '<web.page:path/>/conference/training.lzx?lzt=swf',
		allowfullscreen : 'false',
		width : '1000',
		height : '768',
		id : 'lzapp'
	});
</script>



</body>

</html>