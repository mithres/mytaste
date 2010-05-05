<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/photo.tld"%>
<%@ taglib prefix="web.page" uri="/WEB-INF/tlds/path.tld"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/pageview.tld"%>

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
	<s:action namespace="/user" name="userProfileTitle" executeResult="true"/>
</div>

<div style="float: left; width: 100%;">
	<s:action namespace="/user" name="profileMenu" executeResult="true">
		<s:param name="action">${type}</s:param>
	</s:action>
</div>
</div>
</div>
</div>

<div id="accountContent" style="width: 100%; float: left; margin-top: 10px;">

<s:if test="type.equals('Main')">
	
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
</s:if><s:elseif test="type.equals('Queue')">
	<div style="float:left;width:100%;">
		<s:iterator value="queue.records" var="playListQueue">
			<%@include file="../vod/playlistentryqueue.jsp"%>
		</s:iterator>
	</div>
	<paginator:page totalCount="${queue.recordTotal}"
	pageCount="${pageCount}" currentPage="${pageNumber}"
	action="/user/queue" className="pageable-div pagination"
	innerStyle="margin-top: 50px; float: left;" />
</s:elseif><s:elseif test="type.equals('Payment')">

	<span style="font-weight:bold;font-size: 16px;">Current account balance:</span><span id="accountBalance"><s:property value="userAccount.accountBalance"/></span>
	<form id="depositForm">
		<table style="margin-top:10px;">
			<tr>
				<td colspan="2"><s:text name="vc.accountdeposits.message"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">Card NO:</td>
				<td><input type="text" class="form-input" name="cardId" autocomplete="off"/></td>
			</tr>
			<tr>
				<td style="text-align:right;"><s:text name="vc.accountdeposits.cardpassword"/>:</td>
				<td><input type="text" class="form-input" name="cardPassword" autocomplete="off"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="Submit" onclick="deposit('depositForm');"/> </td>
			</tr>
		</table>		
	</form>
</s:elseif>
</div>

</body>
</html>