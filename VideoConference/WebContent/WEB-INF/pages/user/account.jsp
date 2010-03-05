<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title><s:text name="vc.accountinfo.title"/></title>
</head>
<body>
	
	<s:text name="vc.accountinfo.accountname"/>:<s:property value="userAccount.userName"/><br/>
	<s:text name="vc.accountinfo.realname"/>:<s:property value="userAccount.lastName+userAccount.firstName"/><br/>
	<s:text name="vc.accountinfo.emailaddress"/>:<s:property value="userAccount.email"/><br/>
	<s:text name="vc.accountinfo.accountbalance"/>:<s:property value="userAccount.accountBalance"/><br/>
	
</body>
</html>