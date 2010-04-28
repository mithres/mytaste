<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/security.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Video Share - Video: <s:property value="playList.playListName"/></title>

<script src="<web.page:path/>/js/jquery-ui.custom.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.uni-form.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.ui.stars.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="<web.page:path/>/css/jquery.ui.stars.css" />

<script>

	$(function(){
		$("#ratings").children().not(":radio").hide();
		$("#ratings").stars({
			cancelShow: false,
			callback: function(ui, type, value)
			{
				$.post("demo1.php", {rate: value}, function(data)
				{
					$("#ajax_response").html(data);
				});
			}
		});
	});

	function initPlayer() {
		var vodPlayer = lz.embed.lzapp;
		var playListID = '<s:property value="playListID"/>';
		var sid = '<s:property value="sid"/>';
		var nodeUrl = '<s:property value="nodeUrl"/>';
		var callMethod = "init('" + playListID + "','" + sid + "','" + nodeUrl + "')";
		vodPlayer.callMethod(callMethod);
	}
</script>
</head>

<body onload="initPlayer();">

<div id="vpvideotitle">
<div class="base">

<h1 class="videotitle">
<span class="label">Video:</span>
<span class="name"><s:property value="playList.playListName"/> </span>
<span> | </span>

</h1>

<form id="ratings" action="" method="post">			
	<input type="radio" name="rate" value="1" title="Poor" id="rate1" /> <label for="rate1">Poor</label><br />
	<input type="radio" name="rate" value="2" title="Fair" id="rate2" /> <label for="rate2">Fair</label><br />
	<input type="radio" name="rate" value="3" title="Average" id="rate3" /> <label for="rate3">Average</label><br />
	<input type="radio" name="rate" value="4" title="Good" id="rate4" /> <label for="rate4">Good</label><br />
	<input type="radio" name="rate" value="5" title="Excellent" id="rate5" /> <label for="rate5">Excellent</label><br />
</form>


<div><s:property value="playList.description"/></div>
<div class="clear"></div>
</div>
</div>
<div class="left">

	<script>

		$(function(){
			$("#starify").children().not(":input").hide();
			$("#starify").stars({
				cancelShow: false
			});
		});
	
		lz.embed.swf( {	
			url : '<web.page:path/>/vod/vodplayer.swf8.swf',
			allowfullscreen : 'true',
			width : '640',
			height : '496',
			id : 'lzapp',
			wmode:'opaque'
		});

		$("#stars-wrapper1").stars();
		
	</script>
	
	<div id="vpcomment_wrap">
		<div id="vpcomment">
			<div class="commentArea">
				<div class="nBox" id="commentAreaBox">
					<div style="cursor: pointer;" class="head">
					<h3 id="comment_title" style="border-bottom:none;">Reviews</h3>
					</div>
					
					<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
						Add Comment
					</security:authorize>
				</div>
				<div class="videoComment">
					<s:action namespace="/vod" name="showComments" executeResult="true"/>
				</div>
			</div>
		</div>
		
		
	</div>



</div>


<div class="right">
<div id="likedvideo_wrap">
	<div params="Object id #75" id="vprelationvideo"><div class="vRelated">
	<div class="nBox">
	<div class="head" style="cursor: pointer;padding-left:0px;"><div class="status" style="display: block;"><div class="ico__expand"></div></div><h3 class="title" style="border-bottom:none">You Might Alos Like</h3></div>
	<div class="body" style="display: block;">
	<div class="coll">	
	<div class="videos">
		<s:action namespace="/vod" name="alsoLiked" executeResult="true"/>	
	<div class="f_c"></div>
	
	</div></div></div></div></div>
	</div>
</div>

<div id="recommendedvideo_wrap">
	<div params="Object id #75" id="vprelationvideo"><div class="vRelated">
	<div class="nBox">
	<div class="head" style="cursor: pointer;padding-left:0px;"><div class="status" style="display: block;"><div class="ico__expand"></div></div><h3 class="title" style="border-bottom:none">Recommended</h3></div>
	<div class="body" style="display: block;">
	<div class="coll">	
	<div class="videos">
		<s:action namespace="/vod" name="showRecommented" executeResult="true"/>	
	<div class="f_c"></div>
	</div></div></div></div></div>
	</div>
</div>



	<div><h1>Tags</h1></div>

</div>



</body>

</html>