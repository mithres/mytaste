<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="decorator" content="main" />
		<title><s:text name="vc.playlist.index.title"/></title>
	</head>
<body>
				
<div class="playListRender">
<h1>Popular Videos</h1>
	<s:iterator value="playLists.records" var="playList">
		<%@include file="playlistentryshort.jsp" %>
	</s:iterator>
	<p class="more_link"><a href="#">more</a></p>
</div>

<div class="playListRender">
<h1>Recently Added</h1>
	<s:iterator value="recentlyAddedPlayLists.records" var="playList">
		<%@include file="playlistentryshort.jsp" %>
	</s:iterator>
	<p class="more_link"><a href="#">more</a></p>
</div>


</body>
</html>