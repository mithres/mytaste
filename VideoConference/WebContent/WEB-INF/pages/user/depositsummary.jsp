<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>TopUp Service</title>
</head>
<body>
	
	<s:form namespace="/user" action="deposit">
	
	<input type="hidden" name="account" value="<s:property value="account"/>"/>
	<input type="hidden" name="cardPassword" value="<s:property value="cardPassword"/>"/>
	
	Card Info:<br/>
	Denominations: <s:property value="pointCard.denomination"/><br/>
	Expire Time:<s:date name="pointCard.expireTime" nice="true"/><br/>
	
	<s:submit label="Save" />

</s:form>
	
</body>
</html>