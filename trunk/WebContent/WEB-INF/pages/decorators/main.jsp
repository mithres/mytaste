<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/security.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="Welcome!" /></title>
<link rel="stylesheet" type="text/css"
	href="<web.page:path/>/css/common.css" />

<script src="<web.page:path/>/js/jquery.js" type="text/javascript"
	charset="utf-8"></script>
<script src="<web.page:path/>/js/jquery.easing.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<web.page:path/>/js/loopedSlider.js" type="text/javascript"
	charset="utf-8"></script>
<!--[if IE]><script src="<web.page:path/>/js/excanvas.js" type="text/javascript" charset="utf-8"></script><![endif]-->
<script src="<web.page:path/>/js/jquery.bt.min.js"
	type="text/javascript" charset="utf-8"></script>

<decorator:head />
</head>

<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true" />>


<div style="position: relative;" id="container"
	class="fixed-lg relative">
<div class="section top">
<div class="standard tidy">
<ul style="" class="usernav" id="logged-out-nav">
	<li class="first sign-out-link"><a
		onclick="FloatingLoginForm.showTop(this); return false;"
		id="login-link" href="#" class="utility-link">Login</a></li>
	<li class="signin-border-left"><a class="utility-link"
		href="<web.page:path/>/user/forgot_password">Forgot Password?</a>
	</li>
	<li class="signin-border-left"><a class="utility-link"
		href="<web.page:path/>/signUp/signUpIndex">Sign Up</a></li>
</ul>
<ul class="nv">
	<li id="home" class="first"><a rel="home" href="<web.page:path/>/"><img	height="42" border="0" title="Video Share" src="<web.page:path/>/images/logo.jpg" /></a> 
	<a href="<web.page:path/>/vod/playListIndex">Videos</a> | <a href="<web.page:path/>/conference/roomListIndex">Conferences</a></li>
	
</ul>
</div>
</div>
</div>

<div class="fluid bar">
<div class="container">

<s:if test="#session.MenuStat.menuStat.equals('vod')">
<ul id="topnav">
	<li style="border-left: 1px solid #A5A5A5;font-weight:bold;"><a href="#">Channels</a></li>
	<li style="font-weight:bold;"><a href="<web.page:path/>/vod/popular">Most Popular</a></li>
	<li style="font-weight:bold;"><a href="#">Recently Added</a></li>
	<li style="font-weight:bold;"><a href="#">Collections</a>
	<span> 
	<a href="#">Web	Design</a><a href="#">Development</a><a href="#">Identity</a>
	<a href="#">SEO &amp; Internet Marketing</a><a href="#">Print Design</a> 
	</span>
	</li>
	
</ul>
</s:if><s:elseif test="#session.MenuStat.menuStat.equals('conference')">
<ul id="topnav">
	<li style="border-left: 1px solid #A5A5A5;font-weight:bold;"><a href="#">Channels</a></li>
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
<div id="contentpanel"><decorator:body /></div>

<div style="clear: both;" class="fluid bar">
<div class="fixed-lg container">
<div style="width: 96%; text-align: center; height: 20px;"
	class="searchnav">
<form
	onsubmit="if(this.query.value == '') {return false;} if(!$('footer-search-auto-complete').visible()){SearchTracking.trackSearchForm('bottom_search_form')}"
	method="get" action="http://www.hulu.com/search" name="search_form"
	id="bottom_search_form"><input type="text"
	style="margin-right: -3px;" name="query" autocomplete="off" value=""
	class="search" id="bottom_video_search_term" /> <a
	href="javascript:void(0)"><img border="0" title="Search"
	style="vertical-align: middle; margin-left: -1px; margin-bottom: 2px;"
	src="<web.page:path/>/images/btn-search.gif" id="bottom-nav-search-button" class=""
	alt="Search" /></a> <a
	onclick="if (!$('serp-header')) return true; window.scroll(0,0); var el = $('advanced-search'); if (!el || !el.visible()) toggleAdvancedSearch(); return false"
	href="/search/advanced_search" class="search-footer-link">Advanced
Search</a> <span class="search-footer-spacer">|</span> <a
	onclick="installOpenSearch(); return false" href="javascript:void(0)"
	class="search-footer-link">Search Plugin</a></form>
</div>
</div>
</div>

<span class="fp_home flt" style="margin: 20px 0px 0px 340px;">Copyright
© 2009-2010 Video Share. All rights reserved. Terms of use Powered By <b
	style="color: #5EACA3;">Video Share</b></span>



</body>
</html>