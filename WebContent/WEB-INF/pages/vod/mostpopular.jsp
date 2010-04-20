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
<div id="base-middle-white"><br>

 Video Type: <select name="playListType"><option value="all">All</option><s:iterator value="playListTypes"><option value="<s:property />"><s:property /></option></s:iterator></select> 
 Channel: <select name="channel"><option value="all">All</option><s:iterator value="channelList"><option value="<s:property value="id"/>"><s:property value="channelName" /></option></s:iterator></select> 
 <br />

<s:if test="type.equals('Popular')">
<div id="divTab" style="width: 100%;">
<ul class="tabs">
	<li id="tab1" onclick="TabSwitch('tab1');" class="<s:if test="timeFrame.equals('All')">selectedTab</s:if>"><a href="<web.page:path/>/vod/popular?timeFrame=All">All Time</a></li>
	<li id="tab2" onclick="TabSwitch('tab2');" class="<s:if test="timeFrame.equals('Today')">selectedTab</s:if>"><a href="<web.page:path/>/vod/popular?timeFrame=Today">Today</a></li>
	<li id="tab3" onclick="TabSwitch('tab3');" class="<s:if test="timeFrame.equals('ThisWeek')">selectedTab</s:if>"><a href="<web.page:path/>/vod/popular?timeFrame=ThisWeek">This Week</a></li>
	<li id="tab4" onclick="TabSwitch('tab4');" class="<s:if test="timeFrame.equals('ThisMonth')">selectedTab</s:if>"><a href="<web.page:path/>/vod/popular?timeFrame=ThisMonth">This Month</a></li>
</ul>
</div>
</s:if>

</div>
</div>
</div>

<div id="playListRender"><s:iterator value="playLists.records"
	var="playList" status="stat">
	<%@include file="playlistentryshort.jsp"%>
</s:iterator>

<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/popular" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$('.vidContents').hide();
	});

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