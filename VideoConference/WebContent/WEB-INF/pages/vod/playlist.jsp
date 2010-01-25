<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>
<%@ taglib prefix="photo" uri="/WEB-INF/tlds/photo.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Show PlayList</title>
</head>
<body>

<div id="playListRender"><s:iterator value="playList.records">
	<img src="<photo:photo index="${playListIndex}" photoType="FilmScreenShot" />" width="64" heigh="64"/>
	:<s:property value="fileName" />:
	<s:property value="description" /> -- <a href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>">Play</a>
	<br />
</s:iterator></div>



<paginator:page totalCount="${playList.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/vodr/playList" className="pageable-div pagination"
	theme="ajax" render="playListRender"
	innerStyle="padding-top: 20px; float: left; width: 100%" />

</body>
</html>