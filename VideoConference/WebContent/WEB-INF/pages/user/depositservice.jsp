<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Account Deposits</title>
</head>
<body>
	
	<s:form namespace="/user" action="depositSummary">
	<s:textfield label="Account Name" name="account" />
	<s:textfield label="Card Password" name="cardPassword" />
	<s:submit label="Save" />

</s:form>
	
</body>
</html>