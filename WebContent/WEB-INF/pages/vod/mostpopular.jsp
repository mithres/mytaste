<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Most Popular Videos</title>
</head>
<body>
	
	Video Type: <br/> 
	<a href="<web.page:path/>/vod/popular?timeFrame=All">All Time</a> | <a href="<web.page:path/>/vod/popular?timeFrame=Today">Today</a> | <a href="<web.page:path/>/vod/popular?timeFrame=ThisWeek">This Week</a> | <a href="<web.page:path/>/vod/popular?timeFrame=ThisMonth">This Month</a><br/>
<div class="fluid">
	<div class="fixed-lg container">
		<div id="profile-tabs-container" class="fixed-lg container relative">
			<ul id="profile-tab-container">
				<li class="profile-tab-selected">
					
					<div>Most Popular</div>
					
				</li>
				<li class="profile-tab-unselected">
				<a href="#">
				<div>Highest Rated</div>
				</a>
				</li>
				<li>
				<a href="#"><img border="0" title="Highest Rated RSS link" src="<web.page:path/>/images/btn-rss.gif" id="" class="vl-rss-link" alt="Highest Rated RSS link" style="cursor: pointer;"></a>
				</li>
				<br style="clear: both;">
			</ul>
		</div>
		<div id="base-middle-white">
			<br>
		</div>
	</div>
</div>	

<div id="playListRender">
	<s:iterator value="playLists.records" var="playList" status="stat">
	<%@include file="playlistentryshort.jsp"%>
	</s:iterator>
</div>



<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/playListIndex" className="pageable-div pagination"
	render="playListRender"
	innerStyle="padding-top: 20px; float: left; width: 100%" />

<script type="text/javascript">
$(document).ready(function() {
	$('.vidContents').hide();
});

</script>
	
</body>
</html>