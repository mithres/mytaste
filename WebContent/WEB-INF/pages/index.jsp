<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="vc.home.title" /></title>

</head>
<body class="div">

<script>
	lz.embed.swf( {	
		url : '<web.page:path/>/flashcommons/videoslideshower.swf',
		allowfullscreen : 'false',
		width : '100%',
		height : '350',
		id : 'lzapp',
		wmode:'opaque'
	});
</script>

<div style="margin-top: 1px;" class="fixed-lg relative container main">
	<div id="playListRender">
		<h1>Popular Videos</h1>
		<s:iterator value="playLists.records"
			var="playList" status="stat">
			<%@include file="vod/playlistentryshort.jsp"%>
		</s:iterator>
	</div>
</div>


<script type="text/javascript">

//Call by flash object
function show(id){
	var url = "<web.page:path/>/vod/play?playListID"+id;
	alert(url);
	//location.href = url;
}

$(document).ready(function() {

	$("ul#topnav li").hover(function() { //Hover over event on list item
		$(this).css({ 'background' : 'url(<web.page:path/>/images/subnav-hover.gif) repeat-x'}); //Add background color + image on hovered list item
		$(this).find("span").show(); //Show the subnav
	} , function() { //on hover out...
		$(this).css({ 'background' : 'none'}); //Ditch the background
		$(this).find("span").hide(); //Hide the subnav
	});
	
	$('.vidContents').hide();
	$('#vid').bt({
	trigger: ['mouseover', 'click'],
	contentSelector: "$('#vidContent')",
	positions: ['right', 'left'],
	fill: '#F4F4F4',
	strokeStyle: '#666666', 
	spikeLength: 20,
	spikeGirth: 10,
	width: 350,
	overlap: 0,
	centerPointY: 1,
	cornerRadius: 0, 
	cssStyles: {
		fontFamily: '"Lucida Grande",Helvetica,Arial,Verdana,sans-serif', 
		fontSize: '12px',
		padding: '10px 14px'
	},
	shadow: true,
	shadowColor: 'rgba(0,0,0,.5)',
	shadowBlur: 8,
	shadowOffsetX: 4,
	shadowOffsetY: 4
	});
});
</script>
</body>
</html>