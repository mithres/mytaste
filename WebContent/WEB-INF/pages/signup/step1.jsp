<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="maindecorator" />
<title>Sign Up</title>
</head>
<body>

<div>
<s:actionerror/>
<s:form namespace="/signUp" action="createUser">
	<h1><b>Welcome to Vide Share</b></h1>
	 To begin, please complete the registration form below.<br/>
	 Once your account is created, an email with instructions to verify your email address will be sent.<br/> 
	 You may use your new Video Share account immediately, but you must first verify your email address before contributing to Reviews and Discussions.
	
	<s:textfield label="%{getText('vc.signup.step1.username')}" name="user.userName" />
	<s:password label="%{getText('vc.signup.step1.password')}" name="user.password" />
	<s:password label="%{getText('vc.signup.step1.cpassword')}" name="password" />
	<s:textfield label="%{getText('vc.signup.step1.email')}" name="user.email" />
	<s:textfield label="%{getText('vc.signup.step1.firstname')}" name="user.firstName" />
	<s:textfield label="%{getText('vc.signup.step1.lastname')}" name="user.lastName" />

	<s:submit/>

</s:form>
</div>

</body>
</html>