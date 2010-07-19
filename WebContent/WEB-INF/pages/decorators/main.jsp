<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/security.tld"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="Welcome!" /></title>

<link rel="stylesheet" type="text/css" href="<web.page:path/>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<web.page:path/>/css/jquery-ui-1.8.1.custom.css" />

<script src="<web.page:path/>/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.bt.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.easing.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/thickbox/thickbox.js" type="text/javascript" charset="utf-8"></script>
<!--[if IE]><script src="<web.page:path/>/js/excanvas.js" type="text/javascript" charset="utf-8"></script><![endif]-->

<script src="<web.page:path/>/js/ui/jquery-ui-1.8.1.custom.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/ui/jquery.ui.widget.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/ui/jquery.ui.button.min.js" type="text/javascript" charset="utf-8"></script>


<script src="<web.page:path/>/js/json.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/lps/includes/embed-compressed.js" type="text/javascript"></script>

<script type="text/javascript">
	var webPath = "<web.page:path/>"; 
	$(function() {
		$("input:button, input:submit").button();
	});

	$(document).ready(function() {
		if ($('.vidContents').length>0){
			$('.vidContents').hide();
		}
		
	});
</script>

<decorator:head />
</head>

<body <decorator:getProperty property="body.onload" writeEntireProperty="true" />>

<div style="position: relative;" id="container" class="fixed-lg relative">
	<div class="section top">
		<div class="standard tidy">
			<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
			
			<ul style="" class="usernav" id="logged-out-nav">
				<li class="first sign-out-link">Welcome : <security:authentication property="name"/></li>
				<li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/user/accountInfo">个人信息</a></li>
				<li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/user/queue">播放列表 <span id="queueCount"></span></a></li>
				<security:authorize ifAllGranted="ROLE_ADMIN"><li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/admin/index">系统</a></li></security:authorize>
				<li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/logout">退出</a></li>
			</ul>
			<script type="text/javascript">
					findUserQueueCount();
			</script>
			
			</security:authorize>

			<security:authorize ifNotGranted="ROLE_ADMIN,ROLE_USER">
			<script>
				function showLoginForm(){
					$('#message_box').animate({ opacity:100 },"slow");  
				}
				function hideLoginForm(){
					$('#loading').animate({opacity:0});	
					$('#message_box').animate({opacity:0 }, "slow");
				};
				
				function login() {
			
					$('#loading').animate({opacity:100});	
					$('#errorMessage').html(""); 
					
					var url = "<web.page:path/>/signIn";
					var param = "userName="+$('#userName').val()+"&password="+$('#userPassword').val()+"&ccode="+$('#checkcode').val();
					
					$.ajax( {
						url : url,
						data: param,
						type : 'post',
						dataType : 'json',
						error : function(xml) {
							$('#loading').animate({opacity:0});		
							alert("Login Error.");
						},
						success : function(xml) {
							$('#loading').animate({opacity:0});
							if(xml.success){
								var url = xml.targetUrl;
								location.href = "<web.page:path/>"+url;
							}else{
								$('#errorMessage').html(xml.errors); 
								flushValidateCode();
							}
						}
					});
				}
				
			</script>
			<ul style="" class="usernav" id="logged-out-nav">
				<li class="first sign-out-link"><a onclick="showLoginForm();" id="login-link" href="javascript:void(0);" style="cursor:pointer;" class="utility-link">登录</a></li>
				<!-- li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/user/forgot_password">忘记密码?</a></li -->
				<li class="signin-border-left"><a class="utility-link" href="<web.page:path/>/signUp/signUpIndex">注册</a></li>
				<li>
					<div id="message_box">
						<div style="float:left;height:14px;width:100%;background:url('<web.page:path/>/images/subnav-hover.gif') repeat-x;"><img id="close_message" onclick="hideLoginForm();" style="float:right;cursor:pointer" src="<web.page:path/>/images/close_12em.png"/></div>
						<div style="float:left;width:100%;">
								
										<table width="100%">
											<tr>
												<td><img src="<web.page:path/>/images/int-warning.gif"/></td>
												<td style="text-align:left;">请输入档案号和密码</td>
											</tr>
											<tr>
												<td colspan="2"><span id="errorMessage"></span></td>
											</tr>
											<tr>
												<td class="rp_user flt"><s:text name="vc.index.username" /></td>
												<td><input id="userName" class="form-input" type='text' name='userName'
													value="" /></td>
											</tr>
											<tr>
												<td class="rp_user flt"><s:text name="vc.index.pwssword" /></td>
												<td><input id="userPassword" class="form-input" type='password' name='password' autocomplete="off" /></td>
											</tr>
											<tr>
												<td class="rp_user flt"><s:text name="vc.index.input_checkcode" /></td>
												<td><input autocomplete="off" id="checkcode" class="form-input" type='text' name='ccode' value="" /></td>
											</tr>
											<tr>
												<td colspan="2" style="text-align:left"><a href="javascript:void(0);" onclick="flushValidateCode();"
													title='<s:text name="vc.index.reload_checkcode" />'><img id="ccode" src="signUp/captcha" border="0" /></a></td>
											</tr>
											<tr>
												<td colspan='2'>
													<img style="opacity:0;" id="loading" src="<web.page:path/>/images/loading.gif" border="0"/>
													<input type="button"  onclick="login();" value="Login" class="ui-button ui-widget ui-state-default ui-corner-all"/> 
													<input type="button" onclick="hideLoginForm();" value="Close" class="ui-button ui-widget ui-state-default ui-corner-all"/> 
												</td>
											</tr>
										</table>
			
						</div>
					</div>
				</li>
			</ul>
			</security:authorize>
			
			
			<ul class="nv">
				<li id="home" class="first"><a rel="home" href="<web.page:path/>/"><img	height="42" border="0" title="Video Share" src="<web.page:path/>/images/logo.jpg" /></a> 
				<!--li class="tabvc current" id="videos">
				<a href="<web.page:path/>/vod/playListIndex" class="">
				Videos</a></li>
				<li class="tabvc" id="conferences">
				<a href="<web.page:path/>/conference/roomListIndex">
				Conferences
				</a></li-->
			</ul>
		</div>
	</div>
</div>

<div class="fluid bar">
<div class="container">

<ul id="topnav">
	<li style="border-left: 1px solid #A5A5A5;font-weight:bold;" <s:if test="'Channel'.equals(#session.NavStat)"> class="selected-li" </s:if>><a href="<web.page:path/>/vod/channels">金牌教师</a>
	<%--<span class="channels-dock"> 
		<table>
			<tbody>
				<tr>
					<s:iterator value="#session.MenuStat.channels" status="stat">
						<td><a href="<web.page:path/>/vod/channels?cid=<s:property value="id"/>"><s:property value="channelName"/></a></td>
						<s:if test="(#stat.index+1)%6 == 0"></tr><tr></s:if>
					</s:iterator>
					<s:set name="count" value="#session.MenuStat.channels.size()%6"/>
			        <s:bean name="org.apache.struts2.util.Counter" id="counter">
			          <s:param name="first" value="1" />
			          <s:param name="last" value="6-#count"/>
			          <s:iterator>
			            <td>&nbsp;</td>
			          </s:iterator>
			        </s:bean>         
				</tr>
				
			</tbody>
		</table>
	

	</span>--%>
	</li>

<s:if test="#session.MenuStat.menuStat.equals('vod')">
	<li style="font-weight:bold;" <s:if test="'MostPopular'.equals(#session.NavStat)"> class="selected-li" </s:if>><a href="<web.page:path/>/vod/popular">最受欢迎</a></li>
	<li style="font-weight:bold;" <s:if test="'RecentlyAdded'.equals(#session.NavStat)"> class="selected-li" </s:if>><a href="<web.page:path/>/vod/recentAdded">Recently Added</a></li>
	<li style="font-weight:bold;" <s:if test="'Collection'.equals(#session.NavStat)"> class="selected-li" </s:if>><a href="<web.page:path/>/vod/collections">Collections</a></li>
	<li style="font-weight:bold;" <s:if test="'LiveBroadcast'.equals(#session.NavStat)"> class="selected-li" </s:if>><a href="<web.page:path/>/vod/liveBroadcast">Live Broadcast</a></li>
	
	
</ul>
</s:if><s:elseif test="#session.MenuStat.menuStat.equals('conference')">
	<li style="font-weight:bold;"><a href="#">Most Popular</a> <span> <a href="#">The
	Company</a>  <a href="#">The Team</a>  <a href="#">Careers</a> </span></li>
	<li style="font-weight:bold;"><a href="#">Recently Added</a> <span> <a href="#">What We
	Do</a>  <a href="#">Our Process</a>  <a href="#">Testimonials</a> </span></li>
	<li style="font-weight:bold;"><a href="#">Portfolio</a> <span> <a href="#">Web
	Design</a>  <a href="#">Development</a>  <a href="#">Identity</a>  <a
		href="#">SEO &amp; Internet Marketing</a>  <a href="#">Print
	Design</a> </span></li>
</ul>
</s:elseif>

<div class="searchnav">
<form method="get" action="#" id="search_form" name="search_form">
<input type="text" name="query" autocomplete="off" value=""
	style="margin-right: -3px;" id="video_search_term" /> <a
	href="javascript:void(0)"><img border="0" title="Search"
	src="<web.page:path/>/images/btn-search.gif" id="top-nav-search-button" class=""
	alt="Search" style="cursor: pointer;" /></a></form>
</div>
</div>


</div>

<!--top panel ends here -->

<div id="contentpanel" class="fixed-lg relative container main" style="position: relative; margin-top: 1px;"><decorator:body /></div>

 
<div style="clear: both;" class="fluid bar">
<div class="fixed-lg container">
<div style="width: 96%; text-align: center; height: 20px;"
	class="searchnav">

</div>
</div>
</div>

<span class="fp_home flt" style="margin: 20px 0px 0px 340px;text-align:center;">
(Copyright © 2002-2010) TIANJIN HUAYING SCIENCE TECHNOLOGY AND EDUCATION DEVELOPMENT CO.,LTD.<br>
<a href="http://www.miibeian.gov.cn/" style="color: #8E8B8B;">津ICP备05004622号</a> 　津教备0151号　建议使用IE6.0以上版本及1024*768分辨率 </span>
</span>



</body>
</html>