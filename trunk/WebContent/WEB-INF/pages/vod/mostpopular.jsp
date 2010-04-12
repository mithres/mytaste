<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="photo" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Most Popular Videos</title>
</head>
<body>
	
	Most Popular | Highest Rated (Rss Link) <br/>
	Video Type: <br/> 
	<a href="<web.page:path/>/vod/popular?timeFrame=All">All Time</a> | <a href="<web.page:path/>/vod/popular?timeFrame=Today">Today</a> | <a href="<web.page:path/>/vod/popular?timeFrame=ThisWeek">This Week</a> | <a href="<web.page:path/>/vod/popular?timeFrame=ThisMonth">This Month</a><br/>
	
	
<div id="playListRender">
	<s:iterator value="playLists.records" var="playList" status="stat">
	<!-- s:property value="#stat.index"/-->
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
	
	$('#vidContent').hide();
	$('#vid').bt({
	trigger: ['mouseover', 'click'],
	contentSelector: "$('#vidContent')",
	positions: ['right', 'left'],
	fill: '#F4F4F4',
	strokeStyle: '#666666', 
	spikeLength: 20,
	spikeGirth: 10,
	width: 350,
	overlap: 0,
	centerPointY: 1,
	cornerRadius: 0, 
	cssStyles: {
		fontFamily: '"Lucida Grande",Helvetica,Arial,Verdana,sans-serif', 
		fontSize: '12px',
		padding: '10px 14px'
	},
	shadow: true,
	shadowColor: 'rgba(0,0,0,.5)',
	shadowBlur: 8,
	shadowOffsetX: 4,
	shadowOffsetY: 4
	});
});
</script>
	
</body>
</html>