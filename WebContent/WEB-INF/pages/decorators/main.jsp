<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><decorator:title default="Welcome!" /></title>
<link rel="stylesheet" type="text/css" href="<web.page:path/>/css/common.css">

<decorator:head />

<body <decorator:getProperty property="body.onload" writeEntireProperty="true" /> >
<div id="mainblock">
	<!--inner block starts here -->
	<div id="innerblock">
		<!--top panel starts here -->
		<div id="toppanel">
			<div class="top1 flt">
				<div style="float:left; width:1000px;">
					<div class="tp_align">
						<a href="#"><img src="<web.page:path/>/images/tp_logo.jpg" width="194" height="25" alt="" class="tp_logo flt" /></a>
						<img src="<web.page:path/>/images/tp_border.jpg" width="1" height="29" alt="" class="tp_border flt" />
						<img src="<web.page:path/>/images/tp_share.jpg" width="99" height="12" alt="" class="tp_share flt"  />
					</div>
					<span class="tp_wel">Welcome <b>Guest!</b></span>
				</div>
				<div style="float:left; margin-left:625px; display:inline;">
					<a href="#" class="tp_funn flt">Funny Videos</a>
					<span class="tp_bar flt">|</span>
					<a href="#" class="tp_funn flt">Fun Games</a>
					<span class="tp_bar flt">|</span>					
					<a href="#" class="tp_funn flt">Funny Pictures</a>
					<span class="tp_bar flt">|</span>					
					<a href="#" class="tp_funn flt">Funny Jokes</a>
				</div>
			</div>	
			<div class="top2 flt">
				<div class="tp_ali flt">
					<input type="text" class="tp_txt flt"  />
					<select class="tp_select flt" ><option>Video</option></select>
					<a href="#"><img src="<web.page:path/>/images/tp_search.jpg" width="67" height="27" alt="" class="tp_search flt" /></a>
				</div>
				<a href="#" class="tp_home flt">Home</a>
				<a href="#" class="tp_home flt" style="margin-left:50px;">Upload</a>
				<a href="#" class="tp_home flt">Videos</a>
				<a href="#" class="tp_home flt">Channels</a>
				<a href="#" class="tp_home flt">News</a>
			</div>	
		
		</div>	
		<!--top panel ends here -->	
		<div id="contentpanel">
				<decorator:body />
		</div>
		
		<div id="footer">
			<div class="fp_align flt">
				<a href="<web.page:path/>/home" class="fp_home flt">Home</a>
				<span class="fp_bar flt">|</span>
				<a href="<web.page:path/>/user/accountDeposits" class="fp_home flt">pay</a>
				<span class="fp_bar flt">|</span>
				<a href="<web.page:path/>/user/accountInfo" class="fp_home flt">profile</a>
				<span class="fp_bar flt">|</span>
				<a href="<web.page:path/>/vod/newPlayList" class="fp_home flt">add video</a>
				<span class="fp_bar flt">|</span>
				<a href="<web.page:path/>/vod/playListIndex" class="fp_home flt">video list</a>

			</div>
			<span class="fp_home flt" style="margin:20px 0px 0px 340px;">Copyright © 2009 My Video. All rights reserved. Terms of use</span>
			<span class="fp_power flt">Powered By <b style="color:#5EACA3;">My Video</b></span>
		</div>
	</div>
</div>		
</body>
</html>