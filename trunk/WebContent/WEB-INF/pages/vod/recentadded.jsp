<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="main" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Share - Recently Added Videos</title>
</head>
<body>

<div class="fluid">
	<div class="fixed-lg container">
		<div id="profile-tabs-container" class="fixed-lg container relative">
			<ul id="profile-tab-container">
				<li class="profile-tab-selected">
				<div>Latest</div>
				</li>
				<li class="profile-tab-unselected">
				<div>Feature Content</div>
				</li>
				<li>
					<a href="#">
						<img border="0" onmouseout="this.src='<web.page:path/>/images/btn-rss.gif';" onmouseover="this.src='<web.page:path/>/images/btn-rss-hover.gif';" title="Highest Rated RSS link" src="<web.page:path/>/images/btn-rss.gif" class="vl-rss-link" alt="Highest Rated RSS link" style="cursor: pointer;">
					</a>
				</li>
				<br style="clear: both;">
			</ul>
		</div>
		<s:action id="list" namespace="/vod" name="showPlayListTypeAndChannel" executeResult="true">
			<s:param name="action">RecentlyAdded</s:param>
			<s:param name="parentChannelId">${channel}</s:param>
		</s:action>
	</div>
</div>

<div class="playListRender">
	<s:iterator value="playLists.records" var="playList" status="stat">
		<%@include file="playlistentryshort.jsp"%>
	</s:iterator>
</div>
	
<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/recentAdded" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
	
	
</body>
</html>