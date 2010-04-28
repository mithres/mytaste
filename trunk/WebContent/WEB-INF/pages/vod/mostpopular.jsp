<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Most Popular Videos</title>
</head>
<body>

<div class="fluid">
<div class="fixed-lg container">
<div id="profile-tabs-container" class="fixed-lg container relative">
<ul id="profile-tab-container">
	<li class="<s:if test="type.equals('Popular')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Popular')">Most Popular</s:if><s:else><a style="text-decoration: none;" href="<web.page:path/>/vod/popular">Most Popular</a></s:else></div>
	</li>
	<li class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Highest Rated</s:if><s:else><a style="text-decoration: none;" href="<web.page:path/>/vod/highestRate">Highest Rated</a></s:else></div>
	</li>
	<li><a href="#"><img border="0" title="Highest Rated RSS link" src="<web.page:path/>/images/btn-rss.gif" id="" class="vl-rss-link" alt="Highest Rated RSS link" style="cursor: pointer;"></a></li>
	<br style="clear: both;">
</ul>
</div>

<s:if test="type.equals('Popular')">
	<s:action id="list" namespace="/vod" name="showPlayListTypeAndChannel" executeResult="true">
		<s:param name="action">Popular</s:param>
		<s:param name="timeFrame">${timeFrame}</s:param>
		<s:param name="parentChannelId">${channel}</s:param>
	</s:action>
</s:if><s:elseif test="type.equals('Rate')">
	<s:action id="list" namespace="/vod" name="showPlayListTypeAndChannel" executeResult="true">
		<s:param name="action">Rate</s:param>
		<s:param name="parentChannelId">${channel}</s:param>
	</s:action>
</s:elseif>



<div>

<s:if test="type.equals('Popular')">
<div id="divTab" style="width: 100%;">
<ul class="tabs">
	<li id="All" onclick="TabSwitch('All');" class="<s:if test="timeFrame.equals('All')">selectedTab</s:if>"><a onclick="searchPlayListByCondition('<s:property value="type"/>','All');" href="javascript:void();">All Time</a></li>
	<li id="Today" onclick="TabSwitch('Today');" class="<s:if test="timeFrame.equals('Today')">selectedTab</s:if>"><a onclick="searchPlayListByCondition('<s:property value="type"/>','Today');" href="javascript:void();">Today</a></li>
	<li id="ThisWeek" onclick="TabSwitch('ThisWeek');" class="<s:if test="timeFrame.equals('ThisWeek')">selectedTab</s:if>"><a onclick="searchPlayListByCondition('<s:property value="type"/>','ThisWeek');" href="javascript:void();">This Week</a></li>
	<li id="ThisMonth" onclick="TabSwitch('ThisMonth');" class="<s:if test="timeFrame.equals('ThisMonth')">selectedTab</s:if>"><a onclick="searchPlayListByCondition('<s:property value="type"/>','ThisMonth');" href="javascript:void();">This Month</a></li>
</ul>
</div>
</s:if>

</div>
</div>
</div>

<div class="playListRender">
	<s:iterator value="playLists.records" var="playList" status="stat">
		<%@include file="playlistentryshort.jsp"%>
	</s:iterator>
</div>

<s:if test="type.equals('Popular')">
	<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/popular" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
	
</s:if><s:else>
	<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/highestRate" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
</s:else>



<script type="text/javascript">
	function TabSwitch(selectedTab) {
		jQuery("#divTab .tabs LI[class!='tabspace']").removeClass();
		jQuery("#" + selectedTab).addClass("selectedTab");  
		var tabText = jQuery("#" + selectedTab).text();
		jQuery("#tabContent").empty();
		jQuery("#tabContent").append(tabText);
	}
	
</script>

</body>
</html>