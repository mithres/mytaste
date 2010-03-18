<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="<web.page:path/>/css/headfoot.css" rel="stylesheet" type="text/css" />

<script>
	function tabOn(v) {
		for ( var i = 1; i <= 4; i++) {
			if (i == v) {
				if (document.getElementById("tab" + i).style.display == "") {
					document.getElementById("tab" + i).style.display = "none";
				} else {//否则打开
					document.getElementById("tab" + i).style.display = "";
				}
			} else {
				document.getElementById("tab" + i).style.display = "none";
			}
		}
	}
	function tabOff(id) {
		document.getElementById(id).style.display = "none";
	}
	function webcount() {
		WebCountAjax.getWebCount(getWebCount);
	}
	function getWebCount(data) {
		document.getElementById("webCount0").innerHTML = data;
	}
</script>
<title><decorator:title default="Welcome!" /></title>
<decorator:head />
</head>


<body onload='<decorator:getProperty property="body.onload" />'>

<div id="content">
<div id="head">

<h3><a href="<web.page:path/>/logout"><s:text name="vc.message.logout"/></a></h3>
<div id="nav">
<ul>
	<li><a href="<web.page:path/>/home"	class="green">首页</a></li>
	<li><a href="<web.page:path/>/user/accountDeposits">冲值</a></li>
	<li><a href="<web.page:path/>/user/accountInfo">用户信息</a></li>
	<li><a href="<web.page:path/>/vod/newPlayList">添加视频</a></li>
	<li><a href="<web.page:path/>/vod/playListIndex">视频列表</a></li>
</ul>
</div>

<div id="img"><img src="<web.page:path/>/images/headbg3.gif" width="960"
	height="49" /></div>
<div id="flash">
<table width="960" border="0">
	<tr>
		<td><a href="<text:text key='project.name'/>consult/index.jsp"><img
			src="<web.page:path/>/images/flash.gif" width="733" height="75" border="0" /></a></td>
		<td width="227">
		<table width="100%" height="100%" border="1" cellpadding="1"
			cellspacing="1" bordercolor="#339900" bgcolor="#FFFF99">
			<tr>
				<td valign="top"><MARQUEE direction=up height="60" id=info
					onmouseout=info.start();;; onmouseover=info.stop();;;
					scrollAmount=2 width="99%"> <strong><font
					color="#FF0000"> </font></strong>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="30%" align="center" valign="top" scope="col"><strong><font
							color="#FF0000">5月22日</font></strong></th>
						<th width="70%" align="left" valign="top" scope="col"><strong><font
							color="#FF0000">新三、新八、新九年级集训队报名</font></strong></th>
					</tr>
					<tr>
						<td align="center" valign="top"><strong><font
							color="#FF0000">5月23日</font></strong></td>
						<td align="left" valign="top"><strong><font
							color="#FF0000">新四年级集训队报名 </font></strong></td>
					</tr>
					<tr>
						<td align="center" valign="top"><strong><font
							color="#FF0000">5月24日</font></strong></td>
						<td align="left" valign="top"><strong><font
							color="#FF0000">新五年级集训队报名</font></strong></td>
					</tr>
					<tr>
						<td align="center" valign="top"><strong><font
							color="#FF0000">5月25日</font></strong></td>
						<td align="left" valign="top"><strong><font
							color="#FF0000">新六年级集训队报名</font></strong></td>
					</tr>
					<tr>
						<td align="center" valign="top"><strong><font
							color="#FF0000">5月30日-6月25日</font></strong></td>
						<td align="left" valign="top"><strong><font
							color="#FF0000">新七年级集训队考试报名</font></strong></td>
					</tr>
					<tr>
						<td align="center" valign="top"><strong><font
							color="#FF0000">5月25日</font></strong></td>
						<td align="left" valign="top"><strong><font
							color="#FF0000">新六年级集训队报名</font></strong></td>
					</tr>
				</table>
				</MARQUEE></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
<!--index.jsp--></div>
<div id="main"><decorator:body /></div>
<div id="foot"><img src="<web.page:path/>/images/foot_133.gif"
	width="12" height="13" />
<p><a href="/about/index.jsp">关于我们</a> | <a href="/about/job.jsp">诚聘英才</a>
<br />
地址:耀华科教馆四楼（南京路与营口道交口） 咨询电话：400-676-7890 Email：huayn@yeah.net<br />
(copyright2002) TIANJIN HUAYING SCIENCE TECHNOLOGY AND EDUCATION
DEVELOPMENT CO.,LTD. <br />
<a href="http://www.miibeian.gov.cn/">津ICP备05004622号</a> 津教备0151号
建议使用IE6.0以上版本及1024*768分辨率
</div>
</div>
</body>
</html>