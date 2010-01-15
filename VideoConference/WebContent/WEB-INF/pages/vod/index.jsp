<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VOD Player</title>

<script type="text/javascript"
	src="<web.page:path/>/lps/includes/embed-compressed.js "></script>
<script>
	function initPlayer(id) {
		//lz.flashapp.setCanvasAttribute("id", id, true);
	}
</script>

</head>

<body onLoad="initPlayer('001')">
<script>
	lz.embed.swf( {
		url : '<web.page:path/>/vod/player.lzx?lzt=swf',
		allowfullscreen : 'false',
		bgcolor : '#ffffff',
		width : '400',
		height : '400',
		id : 'lzapp',
		accessible : 'false'
	});
</script>

</body>

</html>