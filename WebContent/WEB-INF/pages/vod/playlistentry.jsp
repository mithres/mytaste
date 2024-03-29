<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<s:iterator value="videos">
	<ul class="video_s">
		<li class="videoImg"><a href="<web.page:path/>/vod/play?playListID=<s:property value="playList.id"/>"><img height="60" border="0" width="75" alt="<s:property value="playList.playListName"/>" src="<p:photo index="${playList.playListIndex}" photoType="FilmScreenShot"/>"></a></li>
		<li class="playMenu" id="<s:property value="type"/><s:property value="playList.id"/>"><img onclick="addToQueue('<s:property value="playList.id"/>');" onmouseout="this.src='<web.page:path/>/images/qls.gif'" onmouseover="this.src='<web.page:path/>/images/qlh.gif'" title="Add this video to your queue" style="display: block;" src="<web.page:path/>/images/qls.gif"></li>
		<li class="videoName">
		<h1><a title="<s:property value="playList.playListName"/>" href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>"><s:property value="playList.playListName"/></a></h1>
		</li>
		<li class="playStat">Play:<span class="num"><s:property value="playList.viewCount"/></span></li>
	</ul>
</s:iterator>