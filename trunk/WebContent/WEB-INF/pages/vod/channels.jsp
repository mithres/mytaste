<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Share - Channels</title>
</head>
<body>
	
	<h1>Video Share Channels</h1>
	Browse all of Video Share's videos by category.<br/>
	
	<s:iterator value="channelList">
		<a href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a><br/>
		<s:iterator value="childChannels">
			<s:property value="channelName"/>
		</s:iterator>
	</s:iterator>
	
</body>
</html>