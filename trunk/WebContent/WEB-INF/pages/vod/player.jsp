<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Video Share - Video: <s:property value="playList.playListName"/></title>

<script>
	function initPlayer() {
		var vodPlayer = lz.embed.lzapp;
		var playListID = '<s:property value="playListID"/>';
		var sid = '<s:property value="sid"/>';
		var nodeUrl = '<s:property value="nodeUrl"/>';
		var callMethod = "init('" + playListID + "','" + sid + "','" + nodeUrl + "')";
		vodPlayer.callMethod(callMethod);
	}
</script>
</head>

<body onload="initPlayer();">
<div>
	<div><h1><s:property value="playList.playListName"/></h1></div>
	<div><s:property value="playList.totalTime"/> | This is rating value</div>
	<div><s:property value="playList.description"/></div>
</div>
<div>
<script>
	lz.embed.swf( {	
		url : '<web.page:path/>/vod/vodplayer.swf8.swf',
		allowfullscreen : 'true',
		width : '640',
		height : '496',
		id : 'lzapp'
	});
</script>
</div>
<div>
<div><a>All</a>|<a>Reviews</a>|<a>Tags</a></div>
<div><h1>You Might Alos Like</h1></div>
<div>xxxxx xxxxxxxxxxxxx</div>
<div><h1>Reviews</h1></div>
<div>
<s:iterator value="comments">
	<s:property value="author.userName"/><s:date name="createdTime" nice="true"/>
</s:iterator>
</div>
<div><h1>Tags</h1></div>

<div><h1>Recommended Videos</h1></div>
<div>dfffffffffffffffff</div>
</div>



</body>

</html>