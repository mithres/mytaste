
	<ul id="vid<s:property value="id"/>" class="video">
		<li class="vImg target">
		<div style="height:80px;width:145px;border: 1px solid #666666;">
		<a onmouseover="javascript:vidContents('<s:property value="id"/>');$('#btnplay<s:property value="id"/>').css('visibility','visible')" id="btnplay<s:property value="id"/>" style="visibility: hidden; left: 47px; top: 30px; height: 32px; width: 33px;" class="play" target="" href="#">
		<img border="0" style="height: 32px; width: 33px;" src="<web.page:path/>/images/btn-play-big.png"></a>
			<a onmouseout="$('#btnplay<s:property value="id"/>').css('visibility','hidden')" onmouseover="javascript:vidContents('<s:property value="id"/>');$('#btnplay<s:property value="id"/>').css('visibility','visible')" name="hotVideoList" href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" class="tipAnchor">
			<img height="80" width="145" alt="<s:property value="playListName"/>" src="<p:photo index="${playList.playListIndex}" photoType="FilmScreenShot"/>"></a>	
		</div>
		</li>		
		<li class="vMenu"><img onmouseout="this.src='<web.page:path/>/images/qls.gif'" onmouseover="this.src='<web.page:path/>/images/qlh.gif'" title="Add this video to your queue" style="display: block;" src="<web.page:path/>/images/qls.gif"></li>
		
		<li class="vTitle" style="padding-top:4px;"><a href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" style="text-decoration: none;font-weight:bold;font-size:14px;" title="<s:property value="playListName"/>"><s:property value="playListName"/></a></li>
		<li class="vTitle">Feature Film: <s:property value="totalTime"/></li>
		<li class="vTitle">More: <s:property value="playListName"/>(Featured Content)</li>
		<li class="vTitle">Channel: <s:property value="channel.channelName"/></li>
		<li class="vTitle">View: <s:property value="viewCount"/></li>
	</ul>
	
	<div id="vidContent<s:property value="id"/>" class="vidContents"> 
		<b><s:property value="playListName"/>:</b><br/>
		Created date: <s:date name="addedTime" nice="true"/><br/>
		Avg. user rating: <br/>
		<s:property value="description"/><br/>
		Tags:<s:property value="tags"/><br/>
	</div>
	

