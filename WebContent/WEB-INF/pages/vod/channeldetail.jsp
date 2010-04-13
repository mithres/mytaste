<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="photo" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Channels - <s:property value="channel.channelName"/></title>
</head>
<body>
	
	<h1><s:property value="channel.channelName"/></h1><br/>
	<s:iterator value="childChannels"><a href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a></s:iterator>
	Search this channel <input ><input type="submit" value="Go"/>
	
	<s:iterator value="playLists.records" var="playList" status="stat">
	<!-- s:property value="#stat.index"/-->
	<%@include file="playlistentryshort.jsp"%>
	</s:iterator>
	
	
</body>
</html>