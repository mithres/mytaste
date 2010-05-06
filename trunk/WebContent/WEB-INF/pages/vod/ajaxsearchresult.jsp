<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<s:if test="playLists.recordTotal > 0">
		<div style="width=100%">
			<span style="font-size: 16px;">
				Result <s:property value="pageCount*pageNumber-pageCount+1"/>-<s:if test="pageCount*pageNumber > playLists.recordTotal"><s:property value="playLists.recordTotal"/></s:if><s:else><s:property value="pageCount*pageNumber"/></s:else> of <s:property value="playLists.recordTotal"/> for <s:property value="text"/></span></div>
		<div class="playListRender">
			<s:iterator value="playLists.records" var="playList" status="stat">
				<%@include file="playlistentryshort.jsp"%>
			</s:iterator>
		</div>
	</s:if><s:else>
		<div style="padding: 100px 100px 20px 55px;" class="no-channel-results">
        No show results for <span style="color: rgb(68, 68, 68);"><s:property value="text"/></span> within the channel: <span style="color: rgb(68, 68, 68);">Animation and Cartoons</span>.
      	</div>
      	<div style="padding: 0pt 45px 100px 0pt; text-align: center; font-size: 13px; font-weight: normal;">
        	<a onclick="#" class="cc_util_link" href="/search?query=afsfsd">See video results</a>
        	for <span style="font-weight: bold;">afsfsd</span> from all channels.
      	</div>
	</s:else>
	
</body>
</html>