<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Channels - <s:property value="channel.channelName"/></title>

<script type="text/javascript" src="<web.page:path/>/js/easyslider1.5.js"></script>




</head>
<body>
	
	<h1><s:property value="channel.channelName"/></h1><br/>
	<s:iterator value="channel.childChannels">
		<a style="text-decoration:none;" href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a>&nbsp;
	</s:iterator>
	
	<s:action namespace="/common" name="channelSlider" executeResult="true">
		<s:param name="channelId">${cid}</s:param>
	</s:action>
	
	<div id="search-bar" class="embed_search_bar">
		<div class="fixed-lg container">
		    <div style="padding: 2px 0pt 0pt 9px; overflow: hidden;" class="video-info">
		      <form  method="post" action="#" name="search_form" id="channel_search_form" style="float: left; width: 96%;">
		        <div style="float: left;padding-top:2px;">
		          <span style="font-size: 11px; font-weight: bold; color: rgb(100, 100, 100); margin-right: 8px; vertical-align: middle;">Search this channel</span>
		          <input type="text" autocomplete="off" name="query" style="vertical-align: middle; width: 230px; height: 17px; border-color: rgb(204, 204, 204) -moz-use-text-color rgb(204, 204, 204) rgb(204, 204, 204); color: rgb(3, 3, 3); border-right: 0px none;" class="search channel_search_term" id="embed-search-bar-query">
		          <a href="javascript:void(0);">
		          <img border="0" onmouseout="this.src='<web.page:path/>/images/button-search-in-channel.gif'" onmouseover="this.src='<web.page:path/>/images/button-search-in-channel-hover.gif'" title="Find specific shows from this channel" style="vertical-align: middle; margin-left: -4px; cursor: pointer;" src="<web.page:path/>/images/button-search-in-channel.gif"  id="embed-search-button" class="" alt=""></a>
		        </div> 
		      </form>
		    </div>
	  	</div>
  	</div>

	<div class="playListRender">
		<s:iterator value="playLists.records" var="playList" status="stat">
			<%@include file="playlistentryshort.jsp"%>
		</s:iterator>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){	
			$("#slider").easySlider();
		});	
	</script>

</body>
</html>