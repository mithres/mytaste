<div>	
	<ul id="vid" class="video">
		<li class="vImg target">
			<a name="hotVideoList" href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" class="tipAnchor">
			<img height="96" width="128" alt="<s:property value="playListName"/>" src="<web.page:path/>/images/generated.jpg"></a>	
		</li>		
		<li class="vMenu"><img onmouseout="this.src='<web.page:path/>/images/qls.gif'" onmouseover="this.src='<web.page:path/>/images/qlh.gif'" title="Add this video to your queue" style="display: block;" src="<web.page:path/>/images/qls.gif"></li>
		<li class="vTitle"><a href="<web.page:path/>/vod/play?playListID=<s:property value="id"/>" style="text-decoration: none;font-weight:bold;font-size:14px;" title="<s:property value="playListName"/>"><s:property value="playListName"/></a></li>
		<li class="vTitle">Feature Film: <s:property value="totalTime"/></li>
		<li class="vTitle">More: <s:property value="playListName"/></li>
		<li class="vTitle">Channel: <s:property value="channel.channelName"/></li>
		<li class="vStat">
			<span title="View" class="ico__statplay"></span><span class="num"><s:property value="viewCount"/></span>&nbsp;&nbsp;
			<span title="Comments" class="ico__statcomment"></span><span class="num"><s:property value="comments.size"/></span>
		</li>
	</ul>
	
	<div id="vidContent"> 
		<b><s:property value="playListName"/>:</b><br/>
		Created date: <s:date name="addedTime" nice="true"/><br/>
		Avg. user rating: <br/>
		<s:property value="description"/><br/>
		Tags:<s:property value="tags"/><br/>
	</div>
	<div class="bt-wrapper " style="position: absolute; width: 350px; height:100px; z-index: 9997; visibility: visible; top: 427.5px; left: 102px;">
	<canvas width="416" height="142" style="position: absolute; z-index: 9998;"></canvas>
	</div>
</div>
