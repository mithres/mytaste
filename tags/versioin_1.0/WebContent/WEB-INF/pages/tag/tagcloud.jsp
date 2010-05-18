<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>


<s:iterator value="cloud.tags()">
	<a href="<s:property value="link"/>"
		style="font-size: <s:property value="weightInt"/>px;"><s:property
		value="name" /></a>
</s:iterator>