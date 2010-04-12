<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.home.title" /></title>



</head>
<body class="div">

<%
	if (session
			.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
		out
				.println(((AuthenticationException) session
						.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
						.getMessage());
	}
%>


<form  action="<web.page:path/>/signIn" method="POST">
							<table width="205">
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.username" /></td>
									<td><input class="rp_txt flt" type='text' name='userName' value="" /></td>
								</tr>
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.pwssword" /></td>
									<td><input class="rp_txt flt" type='password' name='password' AUTOCOMPLETE="off" /></td>
								</tr>
								<tr>
									<td class="rp_user flt"><s:text name="vc.index.input_checkcode" /></td>
									<td><input class="rp_txt flt" type='text' name='ccode' value="" /></td>
								</tr>
								<tr>
									<td colspan="2"><a href="javascript:void(0);" onClick="flushValidateCode();" title='<s:text name="vc.index.reload_checkcode" />'><img id="ccode" src="signUp/captcha" border="0"/></a></td>
								</tr>
								<tr>
									<td colspan='2'>
									<input type="image" src="<web.page:path/>/images/rp_login.jpg"  name='<s:text name="vc.button.submit" />' />									</td>
								</tr>
						  </table>
							
						  </form>
						  
						  
<div style="position: relative; margin-top: 1px;" class="fixed-lg relative container main">
	<div id="loopedSlider" class="clearfix">
		<div class="container_pic">
			<div id="photo-1">
				<img src="<web.page:path/>/images/image-01.jpeg" width="970" height="300" alt="Image 01" />
			</div>
			<div id="photo-2">
				<img src="<web.page:path/>/images/image-02.jpeg" width="970" height="300" alt="Image 02" />
			</div>
			<div id="photo-3">
				<img src="<web.page:path/>/images/image-03.jpeg" width="970" height="300" alt="Image 03" />
			</div>
			<div id="photo-4">
				<img src="<web.page:path/>/images/image-04.jpeg" width="970" height="300" alt="Image 04" />
			</div>
			<div id="photo-5">
				<img src="<web.page:path/>/images/image-05.jpeg" width="970" height="300" alt="Image 05" />
			</div>
			<div id="photo-6">
				<img src="<web.page:path/>/images/image-06.jpeg" width="970" height="300" alt="Image 06" />
			</div>
		</div>
		<ul class="nav-buttons">
			<li class="p"><a href="#" class="previous"><img src="<web.page:path/>/images/previous.png" width="22" height="22" alt="Previous" /></a></li>
			<li class="n"><a href="#" class="next"><img src="<web.page:path/>/images/next.png" width="22" height="22" alt="Next" /></a></li>
		</ul>
		<ul class="pagination">
			<li><a href="#photo-1">one</a></li>
			<li><a href="#photo-2">two</a></li>
			<li><a href="#photo-3">three</a></li>
			<li><a href="#photo-4">four</a></li>
			<li><a href="#photo-5">five</a></li>
			<li><a href="#photo-6">six</a></li>
		</ul>
	</div>
	
<div>	

	<ul id="vid" class="video">
		<li class="vImg target">
			<a name="hotVideoList" id="XMTYyOTgyMjky" target="video" href="#" class="tipAnchor">
			<img height="96" width="128" alt="xxxx xxxx xxxx xx " src="<web.page:path/>/images/generated.jpg"></a>	
		</li>		
		<li class="vMenu"><img id=""  onmouseout="this.src='<web.page:path/>/images/qls.gif'" onmouseover="this.src='<web.page:path/>/images/qlh.gif'" title="Add this video to your queue" style="display: block;" src="<web.page:path/>/images/qls.gif"></li>
		<li class="vTitle"><a charset="100-003-1" target="video" href="#" title="xxxxxxxxx ">Video Share</a></li>
		<li class="vStat">
			<span title="" class="ico__statplay"></span><span class="num">144,209</span>&nbsp;&nbsp;
			<span title="" class="ico__statcomment"></span><span class="num">534</span>
		</li>
	</ul>
	

	<div id="vidContent"> 
		<b>BeautyTips:</b> Pretty Tooltips For jQuery<br />
		Season 1, Ep. 1 (00:48)<br />
		Air date: 05/20/2009  |  Rated: TV-14  |  ☆☆☆☆☆<br />
		<hr />Call them tooltips, talk bubbles, or help balloons, we're making them quick and pretty - without any graphics slicing or careful CSS layering. 
	</div>
	<div class="bt-wrapper " style="position: absolute; width: 350px; height:100px; z-index: 9997; visibility: visible; top: 427.5px; left: 102px;">
	<canvas width="416" height="142" style="position: absolute; z-index: 9998;"></canvas>
	</div>
</div>


	
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	
	$("ul#topnav li").hover(function() { //Hover over event on list item
		$(this).css({ 'background' : 'url(<web.page:path/>/images/subnav-hover.gif) repeat-x'}); //Add background color + image on hovered list item
		$(this).find("span").show(); //Show the subnav
	} , function() { //on hover out...
		$(this).css({ 'background' : 'none'}); //Ditch the background
		$(this).find("span").hide(); //Hide the subnav
	});
	
	
	$(function(){
		$('#loopedSlider').loopedSlider({
			container : 'container_pic',
			slideClass: 'photo',
			autoHeight: false,
			fadeSpeed: 250,
			slideSpeed: 150
		});
	});	
	
	$('#vidContent').hide();
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