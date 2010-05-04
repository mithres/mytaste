<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.accountinfo.title" /></title>
</head>
<body>

<div class="fluid">
<div class="fixed-lg container">
<div id="profile-tabs-container" class="fixed-lg container relative">
<div style="width: 100%">
<div class="avatar"
	style="width: 150px; text-align: center; margin-bottom: 5px;"><a
	href="#"> <img title="Change your profile photo"
	<s:if test="userAccount.uploadedAvatar">src="<p:photo index="${userAccount.userIndex}" photoType="UserPhoto"/>"</s:if>
	<s:else>src="<web.page:path/>/images/avatar.png"</s:else> /> </a></div>
<div class="content">
<h1>Your Profile</h1>
<div style="margin-top: 20px;"><a
	href="<web.page:path/>/user/avatar" style="text-decoration: none;">[
Change your profile photo ]</a></div>
</div>
<div style="float: right; margin-top: 7px; width: 200px;"><span
	style="font-size: 90%;"> <a href="#">[ See how others view
your profile ]</a> </span></div>
</div>
<div style="float: left; width: 100%;">
<ul id="profile-tab-container">
	<li
		class="<s:if test="type.equals('Main')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Main')">Main</s:if><s:else>
		<a style="text-decoration: none;" href="<web.page:path/>/vod/popular">Main</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Queue')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Queue')">Queue </s:if><s:else>
		<a style="text-decoration: none;"
			href="<web.page:path/>/user/queue">Queue (<s:property value="count"/>)</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Tags</s:if><s:else>
		<a style="text-decoration: none;"
			href="<web.page:path/>/vod/highestRate">Tags</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Balance</s:if><s:else>
		<a style="text-decoration: none;"
			href="<web.page:path/>/vod/highestRate">Balance</a>
	</s:else></div>
	</li>
	<li
		class="<s:if test="type.equals('Rate')">profile-tab-selected</s:if><s:else>profile-tab-unselected</s:else>">
	<div><s:if test="type.equals('Rate')">Setting</s:if><s:else>
		<a style="text-decoration: none;"
			href="<web.page:path/>/vod/highestRate">Setting</a>
	</s:else></div>
	</li>
	<br style="clear: both;">
</ul>
</div>
</div>
</div>
</div>

<div style="width: 100%; float: left; margin-top: 10px;">
<table>
	<tr>
		<td>Real Name :</td>
		<td><s:property value="userAccount.lastName"/> <s:property value="userAccount.firstName"/></td>
	</tr>
	<tr>
		<td>Email Address :</td>
		<td><s:property value="userAccount.email" /></td>
	</tr>
	<tr>
		<td>Account balance :</td>
		<td><s:property value="userAccount.accountBalance" /></td>
	</tr>
</table>
</div>


</body>
</html>