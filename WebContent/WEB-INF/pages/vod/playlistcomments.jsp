<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<div class="comments">
	<s:iterator value="videoComments.records">
	<div class="comment">
		<div class="bar">
			<a name="" id="" target="_blank" href="#"><s:property value="author.firstName"/> <s:property value="author.lastName"/></a>&nbsp;&nbsp;<s:date name="createdTime" nice="true"/>
		</div>
		<div class="content">
			<span><s:property value="content"/><br></span>
		</div>
	</div>
	</s:iterator>
</div>