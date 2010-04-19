<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="decorator" content="maindecorator" />
		<title><s:text name="vc.playlist.index.title"/></title>
	</head>
<body>

<div class="cp_align flt">
	<div class="cp_tab flt">
		<a href="#" class="cp_fea flt">Most Popular</a>
		<a href="#" class="cp_feas flt">Highest Rated<img src="<web.page:path/>/images/cp_arrows.jpg" width="10" height="7" alt="" /></a>
	</div>
</div>
				
<div id="playListRender">
	<s:iterator value="playLists.records" var="playList">
		<%@include file="playlistentryshort.jsp" %>
	</s:iterator>
</div>



<paginator:page totalCount="${playLists.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vod/playListIndex" className="pageable-div pagination"
	render="playListRender"
	innerStyle="padding-top: 20px; float: left; width: 100%" />

</body>
</html>