<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Sign Up</title>
</head>
<body>
<s:actionerror/>
<s:form namespace="/signUp" action="createUser">

	<s:textfield label="User Name" name="user.userName" />
	<s:password label="User Password" name="user.password" />
	<s:password label="Confirm Password" name="password" />
	<s:textfield label="User Email" name="user.email" />
	<s:textfield label="First Name" name="user.firstName" />
	<s:textfield label="Last Name" name="user.lastName" />

	<s:submit label="Save" />

</s:form>


</body>
</html>