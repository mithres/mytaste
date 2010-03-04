<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.accountdeposits.pointcardinfo.title"/></title>
</head>
<body>
	
	<s:form namespace="/user" action="deposit">
	
	<input type="hidden" name="account" value="<s:property value="account"/>"/>
	<input type="hidden" name="cardPassword" value="<s:property value="cardPassword"/>"/>
	
	<s:text name="vc.accountdeposits.accountinfo"/><br/>
	<s:text name="vc.accountdeposits.account"/>:<s:property value="account"/><br/>
	<s:text name="vc.accountdeposits.accountbalance"/>:<s:property value="accountBalance"/><br/>
	
	<s:text name="vc.accountdeposits.cardinfo"/>:<br/>
	<s:text name="vc.accountdeposits.carddenominations"/>: <s:property value="pointCard.denomination"/><br/>
	<s:text name="vc.accountdeposits.cardexpiretime"/>:<s:date name="pointCard.expireTime" nice="true"/><br/>
	
	<s:submit type="button"/>

</s:form>
	
</body>
</html>