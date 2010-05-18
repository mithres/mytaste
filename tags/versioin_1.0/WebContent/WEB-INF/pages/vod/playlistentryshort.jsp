
	<div style="float:left;width:240px;">
		<ul id="vid<s:property value="id"/>" class="video">
			<li class="vImg target">
			<a onmouseover="javascript:vidContents('<s:property value="id"/>');$('#btnplay<s:property value="id"/>').css('visibility','visible')" id="btnplay<s:property value="id"/>" style="visibility: hidden; left: 57px; top: 28px; height: 32px; width: 33px;" class="play" target="" href="#">
			<img border="0" style="height: 32px; width: 33px;" src="<web.page:path/>/images/btn-play-big.png"></a>
				<a onmouseout="$('#btnplay<s:property value="id"/>').css('visibility','hidden')" onmouseover="javascript:vidContents('<s:property value="id"/>');$('#btnplay<s:property value="id"/>').css('visibility','visible')" name="hotVideoList" href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" class="tipAnchor">
				<img height="80" width="145" alt="<s:property value="playListName"/>" src="<p:photo index="${playList.playListIndex}" photoType="FilmScreenShot"/>"></a>	
			</li>		
			
			<li class="vTitle"><a href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" style="text-decoration: none;font-weight:bold;font-size:14px;" title="<s:property value="playListName"/>"><s:property value="playListName"/></a></li>
			<li class="vTitle">Total Time: <s:property value="totalTime"/></li>
			<!-- li class="vTitle">More: <s:property value="playListName"/>(Featured Content)</li-->
			<li class="vTitle">Channel: <s:property value="channel.channelName"/></li>
			<li class="vTitle">View: <s:property value="viewCount"/></li>
		</ul>
		<div class="vMenu" id="queueState<s:property value="id"/>" ><security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER"><img onclick="addToQueue('<s:property value="id"/>','queueState<s:property value="id"/>');" onmouseout="this.src='<web.page:path/>/images/qls.gif'" onmouseover="this.src='<web.page:path/>/images/qlh.gif'" title="Add this video to your queue" style="display: block;" src="<web.page:path/>/images/qls.gif"></security:authorize><security:authorize ifAllGranted="ROLE_ADMIN"><a href="<web.page:path/>/vod/edit?playListId=<s:property value="id"/>">Edit</a></security:authorize></div>
	</div>
	<div id="vidContent<s:property value="id"/>" class="vidContents"> 
		<b><s:property value="playListName"/>:</b><br/>
		Created date: <s:date name="addedTime" nice="true"/><br/>
		Avg. user rating: <br/>
		<s:property value="description"/><br/>
		Tags:<s:iterator value="tags"><s:property value="tag"/></s:iterator>
		<br/>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.vidContents').hide();
	});
	</script>

