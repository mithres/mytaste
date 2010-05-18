<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.accountdeposits.title"/></title>
</head>
<body>
	
	<s:text name="vc.accountdeposits.message"/>
	<s:form id="depositForm" namespace="/user" action="depositSummary">
	<s:textfield label="%{getText('vc.accountdeposits.account')}" name="account" />
	<s:textfield label="%{getText('vc.accountdeposits.cardpassword')}" name="cardPassword" />
	<s:submit/>

</s:form>
	
</body>
</html>