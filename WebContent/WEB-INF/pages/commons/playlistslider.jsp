<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<div align="center" style="margin-top:20px;">
	<div id="slider" style="width:870px;">
		<s:if test="playLists.recordTotal > 0">
			<ul id="sliderul">				
				<s:iterator value="playLists.records">
					<li><a href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>"><img height="160" width="290" src="<p:photo index="${playListIndex}" photoType="FilmScreenShot"/>" /></a></li>
				</s:iterator>	
			</ul>
		</s:if>	
	</div>
</div>