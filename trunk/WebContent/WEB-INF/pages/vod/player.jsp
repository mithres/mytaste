<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/security.tld"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="main" />
<title>华英视频教学: <s:property value="playList.playListName"/></title>

<script src="<web.page:path/>/js/swfobject.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery-ui.custom.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.uni-form.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.ui.stars.js" type="text/javascript" charset="utf-8"></script>


<link rel="stylesheet" type="text/css" href="<web.page:path/>/css/jquery.ui.stars.css" />

</head>

<body onload="">
<div id="vpvideotitle">
<div class="base">

<table>
<tr>
	<td><h1 class="videotitle">
<span class="label"><s:property value="playList.channel.channelName"/>:</span>
<span class="name"><s:property value="playList.playListName"/> </span>
<span> | </span>

</h1></td>
	<td><form id="ratings" action="<web.page:path/>/vod/ratePlayList" method="post">
<input type="radio" id="rate1" name="rateValue" value="1" title="Poor" id="rate1" <s:if test="playList.averageRateValue != null && playList.averageRateValue.intValue() == 1">checked="checked"</s:if> />
		<input type="radio" id="rate2" name="rateValue" value="2" title="Fair" id="rate2" <s:if test="playList.averageRateValue != null && playList.averageRateValue.intValue() == 2">checked="checked"</s:if> />
		<input type="radio" id="rate3" name="rateValue" value="3" title="Average" id="rate3" <s:if test="playList.averageRateValue != null && playList.averageRateValue.intValue() == 3">checked="checked"</s:if>  />
		<input type="radio" id="rate4" name="rateValue" value="4" title="Good" id="rate4" <s:if test="playList.averageRateValue != null && playList.averageRateValue.intValue() == 4">checked="checked"</s:if> />
		<input type="radio" id="rate5" name="rateValue" value="5" title="Excellent" id="rate5" <s:if test="playList.averageRateValue != null && playList.averageRateValue.intValue() == 5">checked="checked"</s:if> />
</form></td>
</tr>

</table>







<div><s:property value="playList.description"/></div>
<div class="clear"></div>
</div>
</div>
<div class="left">


<div id="flashContent">

</div>
	<script>
	//url : '<web.page:path/>/conference/training.lzx?lzt=swf',
	//	lz.embed.swf( {	
	//		url : '<web.page:path/>/vod/vodplayer.lzx?lzt=swf',	
	//		allowfullscreen : 'true',
	//		width : '640',
	//		height : '514',
	//		id : 'lzapp',
	//		wmode:'opaque'
	//	});
        </script>

	<div id="vpcomment_wrap">
		<div id="vpcomment">
			<div class="commentArea">
				<div class="nBox" id="commentAreaBox">
					<div style="cursor: pointer;" class="head">
						<div style="float:left;"><h3 id="comment_title" style="border-bottom:none; width:50px;">Reviews</h3></div>
						<div style="float:right;">
							<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER"><a href="javascript:void(0)" onclick="showReview();">Create a new review</a></security:authorize>
						</div>
					</div>
					
				</div>
				<div class="videoComment">
					<s:action namespace="/vod" name="showComments" executeResult="true"/>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="right">
<!-- 
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
 -->
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



<div class="tag-cloud">
	<s:action namespace="/tag" name="tagCloud" executeResult="true"/>	
</div>

</div>

<script>
	
	$(function(){
		$("#ratings").children().not(":radio").hide();
		$("#ratings").stars({
			cancelShow: false,
			callback: function(ui, type, value){
				var playListId = "<s:property value="playListID"/>";
				$.post("<web.page:path/>/vod/ratePlayList", {rateValue: value,playListId:playListId}, function(data){});
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


    var swfVersionStr = "10.0.0";
    var xiSwfUrlStr = "<web.page:path/>/vod/playerProductInstall.swf";
    
    var flashvars = {};
    flashvars.sid='<s:property value="sid"/>';
    flashvars.vodurl='<s:property value="nodeUrl"/>';
    flashvars.pid='<s:property value="playListID"/>';
    
    var params = {};
    params.quality = "high";
    params.bgcolor = "#ffffff";
    params.allowscriptaccess = "sameDomain";
    params.allowfullscreen = "true";
    params.wmode = "opaque";
    
    
    var attributes = {};
    attributes.id = "MyVideoPlayer";
    attributes.name = "MyVideoPlayer";
    attributes.align = "middle";

    swfobject.embedSWF(
        "<web.page:path/>/vod/Player.swf", "flashContent", 
        "500", "500", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:center;");
	swfobject.switchOffAutoHideShow();
	
</script>

</body>

</html>