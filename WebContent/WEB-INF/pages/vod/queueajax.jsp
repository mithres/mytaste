<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>

<div style="float:left;width:100%;">
	<s:iterator value="queue.records" var="playListQueue">
		<%@include file="../vod/playlistentryqueue.jsp"%>
	</s:iterator>
</div>