<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Video Share - Video: <s:property value="playList.playListName"/></title>

<script>
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
<span> | ★★★☆☆</span>
</h1>

<div class="clear"></div>
</div>
</div>
<div class="left">

	<script>
		lz.embed.swf( {	
			url : '<web.page:path/>/vod/vodplayer.swf8.swf',
			allowfullscreen : 'true',
			width : '640',
			height : '496',
			id : 'lzapp'
		});
	</script>
	<div><s:property value="playList.description"/></div>
	<div id="vpcomment_wrap">
		<div id="vpcomment">
			<div class="commentArea">
				<div class="nBox" id="commentAreaBox">
					<div style="cursor: pointer;" class="head">
					<h3 id="comment_title" style="border-bottom:none;">Reviews</h3>
					</div>
				</div>
				<div class="videoComment">
				<div class="comments">
					<s:iterator value="comments">
					<div id="" class="comment">
						<div class="bar">
							<a name="" id="" target="_blank" href="#"><s:property value="author.userName"/>(user)</a>&nbsp;&nbsp;<s:date name="createdTime" nice="true"/>(one hours)
						</div>
						<div class="content">
							<span>good<br></span>
						</div>
					</div>
					</s:iterator>
					
					<div id="" class="comment">
						<div class="bar">
							<a name="" id="" target="_blank" href="#"><s:property value="author.userName"/>(user)</a>&nbsp;&nbsp;<s:date name="createdTime" nice="true"/>(one hours)
						</div>
						<div class="content">
							<span>good<br></span>
						</div>
					</div>
					<div id="" class="comment">
						<div class="bar">
							<a name="" id="" target="_blank" href="#"><s:property value="author.userName"/>(user)</a>&nbsp;&nbsp;<s:date name="createdTime" nice="true"/>(one hours)
						</div>
						<div class="content">
							<span>good<br></span>
						</div>
					</div>
				</div>
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
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTY1Mjc0OTI0.html" charset="400-9-1-1"><img height="60" border="0" width="75" alt="[优酷拍客]美腿女郎，你为何躺在地上" src="http://g1.ykimg.com/0100641F464BC6936843B400CE9B43D5E379FE-19EA-DCAD-779D-33A7395BF99E"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTY1Mjc0OTI0" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="[优酷拍客]美腿女郎，你为何躺在地上" charset="400-9-1-1" target="video" href="http://v.youku.com/v_show/id_XMTY1Mjc0OTI0.html">[优酷拍客]美腿女郎，你为何躺在地上</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">1,823,853</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTY1MjE4NzI0.html" charset="400-9-2-1"><img height="60" border="0" width="75" alt="虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈" src="http://g2.ykimg.com/0100641F464BC617C1BA47038C93C05A38657D-8FFF-59BE-F85C-88DA4AE1D049"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTY1MjE4NzI0" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈" charset="400-9-2-1" target="video" href="http://v.youku.com/v_show/id_XMTY1MjE4NzI0.html">虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">195,008</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTM4NTgxNTgw.html" charset="400-9-3-1"><img height="60" border="0" width="75" alt="有线怪谈【泰国不思议手记大头怪婴】2009-11-7" src="http://g3.ykimg.com/0100641F464B143D96CFD6018F3483B352A55F-82AA-4A01-893A-AB9F372E2267"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTM4NTgxNTgw" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="有线怪谈【泰国不思议手记大头怪婴】2009-11-7" charset="400-9-3-1" target="video" href="http://v.youku.com/v_show/id_XMTM4NTgxNTgw.html">有线怪谈【泰国不思议手记大头怪婴】200...</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">29,987</span></li>
	</ul>
	<div class="f_c"></div>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XODgwODgzMDQ=.html" charset="400-9-4-1"><img height="60" border="0" width="75" alt="会唱歌跳舞说话的美女机器人" src="http://g1.ykimg.com/0100641F4649F9206C3AA3018FD49A3054D2CC-BD5C-EC50-1D29-68F86D276641"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XODgwODgzMDQ=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="会唱歌跳舞说话的美女机器人" charset="400-9-4-1" target="video" href="http://v.youku.com/v_show/id_XODgwODgzMDQ=.html">会唱歌跳舞说话的美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">4,055</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XNzk2MTQ0MjA=.html" charset="400-9-5-1"><img height="60" border="0" width="75" alt="美女机器人" src="http://g1.ykimg.com/0100641F4649C199C4086E00BA7FE0A909F3E9-091E-1FF8-D510-51BFD79513F9"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XNzk2MTQ0MjA=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="美女机器人" charset="400-9-5-1" target="video" href="http://v.youku.com/v_show/id_XNzk2MTQ0MjA=.html">美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">12,030</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMjk0OTA1ODg=.html" charset="400-9-6-1"><img height="60" border="0" width="75" alt="日本美女机器人2" src="http://g4.ykimg.com/0100641F464B2ECEBBDF6E00AAF915A8B4477A-7E4F-5E9F-5A13-4061DDACEC0C"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMjk0OTA1ODg=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="日本美女机器人2" charset="400-9-6-1" target="video" href="http://v.youku.com/v_show/id_XMjk0OTA1ODg=.html">日本美女机器人2</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">357,352</span></li>
	</ul>
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
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTY1Mjc0OTI0.html" charset="400-9-1-1"><img height="60" border="0" width="75" alt="[优酷拍客]美腿女郎，你为何躺在地上" src="http://g1.ykimg.com/0100641F464BC6936843B400CE9B43D5E379FE-19EA-DCAD-779D-33A7395BF99E"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTY1Mjc0OTI0" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="[优酷拍客]美腿女郎，你为何躺在地上" charset="400-9-1-1" target="video" href="http://v.youku.com/v_show/id_XMTY1Mjc0OTI0.html">[优酷拍客]美腿女郎，你为何躺在地上</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">1,823,853</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTY1MjE4NzI0.html" charset="400-9-2-1"><img height="60" border="0" width="75" alt="虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈" src="http://g2.ykimg.com/0100641F464BC617C1BA47038C93C05A38657D-8FFF-59BE-F85C-88DA4AE1D049"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTY1MjE4NzI0" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈" charset="400-9-2-1" target="video" href="http://v.youku.com/v_show/id_XMTY1MjE4NzI0.html">虫虫在巴黎香榭丽舍大街吃海鲜餐哈哈</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">195,008</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTM4NTgxNTgw.html" charset="400-9-3-1"><img height="60" border="0" width="75" alt="有线怪谈【泰国不思议手记大头怪婴】2009-11-7" src="http://g3.ykimg.com/0100641F464B143D96CFD6018F3483B352A55F-82AA-4A01-893A-AB9F372E2267"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTM4NTgxNTgw" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="有线怪谈【泰国不思议手记大头怪婴】2009-11-7" charset="400-9-3-1" target="video" href="http://v.youku.com/v_show/id_XMTM4NTgxNTgw.html">有线怪谈【泰国不思议手记大头怪婴】200...</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">29,987</span></li>
	</ul>
	<div class="f_c"></div>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XODgwODgzMDQ=.html" charset="400-9-4-1"><img height="60" border="0" width="75" alt="会唱歌跳舞说话的美女机器人" src="http://g1.ykimg.com/0100641F4649F9206C3AA3018FD49A3054D2CC-BD5C-EC50-1D29-68F86D276641"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XODgwODgzMDQ=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="会唱歌跳舞说话的美女机器人" charset="400-9-4-1" target="video" href="http://v.youku.com/v_show/id_XODgwODgzMDQ=.html">会唱歌跳舞说话的美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">4,055</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XNzk2MTQ0MjA=.html" charset="400-9-5-1"><img height="60" border="0" width="75" alt="美女机器人" src="http://g1.ykimg.com/0100641F4649C199C4086E00BA7FE0A909F3E9-091E-1FF8-D510-51BFD79513F9"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XNzk2MTQ0MjA=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="美女机器人" charset="400-9-5-1" target="video" href="http://v.youku.com/v_show/id_XNzk2MTQ0MjA=.html">美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">12,030</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMjk0OTA1ODg=.html" charset="400-9-6-1"><img height="60" border="0" width="75" alt="日本美女机器人2" src="http://g4.ykimg.com/0100641F464B2ECEBBDF6E00AAF915A8B4477A-7E4F-5E9F-5A13-4061DDACEC0C"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMjk0OTA1ODg=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="日本美女机器人2" charset="400-9-6-1" target="video" href="http://v.youku.com/v_show/id_XMjk0OTA1ODg=.html">日本美女机器人2</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">357,352</span></li>
	</ul>
	<div class="f_c"></div>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMTM4MzM3MzAw.html" charset="400-9-7-1"><img height="60" border="0" width="75" alt="出仿真机器人美女 惊艳" src="http://g2.ykimg.com/0100641F464B5C7B199DD901E2CBAD2094F93A-4283-4F30-5722-BAC1A5CB4372"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMTM4MzM3MzAw" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="出仿真机器人美女 惊艳" charset="400-9-7-1" target="video" href="http://v.youku.com/v_show/id_XMTM4MzM3MzAw.html">出仿真机器人美女 惊艳</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">6,730</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XNzE1MDgwNA==.html" charset="400-9-8-1"><img height="60" border="0" width="75" alt="超级逼真的美女机器人" src="http://g3.ykimg.com/0100641F464B3843DEEF3100217BCC95BEB329-FFC5-8B94-1F63-C727623D93D4"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XNzE1MDgwNA==" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="超级逼真的美女机器人" charset="400-9-8-1" target="video" href="http://v.youku.com/v_show/id_XNzE1MDgwNA==.html">超级逼真的美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">67,396</span></li>
	</ul>
	<ul class="video_s">
	<li class="videoImg"><a target="video" href="http://v.youku.com/v_show/id_XMjI4NDk0NjA=.html" charset="400-9-9-1"><img height="60" border="0" width="75" alt="最逼真的美女机器人" src="http://g4.ykimg.com/0100011F4647F25653F261009B1CA3DD1EEC31-5A9F-D748-6FF6-79F764DBA61E"></a></li>
	<li class="playMenu"><img onclick="PlayList.render(this,PlayListIndexCallback);PlayList.init();nova_update('PlayListMini','/v/showPlayListMini/id/41159601');" id="PlayListFlag_XMjI4NDk0NjA=" title="添加到点播单" style="display: block;" class="QLiconB_small" src="http://static.youku.com/v/img/qls.gif"></li>
	<li class="videoName">
	<h1><a title="最逼真的美女机器人" charset="400-9-9-1" target="video" href="http://v.youku.com/v_show/id_XMjI4NDk0NjA=.html">最逼真的美女机器人</a></h1>
	</li>
	<li class="playStat">播放:<span class="num">12,747</span></li>
	</ul>
	<div class="f_c"></div>
	</div></div></div></div></div>
	</div>
</div>



	<div><h1>Tags</h1></div>

</div>



</body>

</html>