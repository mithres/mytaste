<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="main" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Share - Channels</title>
<script type="text/javascript" src="<web.page:path/>/js/easyslider1.5.js"></script>
</head>
<body>
	<h1>Video Share Channels</h1>
	<h4 class="subtag">Browse all of Video Share's videos by category.</h4>
	
	<s:action namespace="/common" name="channelSlider" executeResult="true"/>
	
	<div class="channel-groups">
		<table class="channels-index">
	        <tbody>
	        	<tr>
	        	<s:iterator value="channelList" status="stat">
		          <td style="padding-left: 0pt; width: 320px;text-align:left;" valign="top">
		          	<div class="channel-group">
					<div class="channel-group-title">
					<h3 style="border-bottom:none;"><a href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a></h3>
					</div>
					<div style="margin-left:20px;">
						<s:iterator value="childChannels">
							<a style="text-decoration:none;" href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a><br>
						</s:iterator>
					</div>
					</div>
		          </td>
		          <s:if test="(#stat.index+1)%3 == 0"></tr><tr></s:if>
	         	</s:iterator>
	          	</tr>
	        </tbody>
	    </table>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){	
			$("#slider").easySlider();
		});	
	</script>
	
</body>
</html>