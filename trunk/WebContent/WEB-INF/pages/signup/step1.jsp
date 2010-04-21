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


<div class="fluid">
	<h1><b>Welcome to Vide Share</b></h1>
	<h4 class="subtag" style="margin-top: 5px;"> To begin, please complete the registration form below.</h4>
	<h4 class="subtag" style="margin-top: 5px;"> Once your account is created, an email with instructions to verify your email address will be sent.<br/> 
	 You may use your new Video Share account immediately, but you must first verify your email address before contributing to Reviews and Discussions.</h4>
</div>

<div style="position: relative;" class="fixed-lg relative container">
  
<div class="form-table-wrapper">
	<br>Please note: Items marked with * are required.</br>
		<s:form namespace="/signUp" action="createUser">
			<s:textfield label="%{getText('vc.signup.step1.username')}" name="user.userName" cssClass="form-input" />
			<s:password label="%{getText('vc.signup.step1.password')}" name="user.password" cssClass="form-input" />
			<s:password label="%{getText('vc.signup.step1.cpassword')}" name="password" cssClass="form-input" />
			<s:textfield label="%{getText('vc.signup.step1.email')}" name="user.email" cssClass="form-input" />
			<s:textfield label="%{getText('vc.signup.step1.firstname')}" name="user.firstName" cssClass="form-input" />
			<s:textfield label="%{getText('vc.signup.step1.lastname')}" name="user.lastName" cssClass="form-input" />
			<s:submit/>
		</s:form>
</div>
<div class="form-table-wrapper">
	<br>Already have an account? Login here:</br>
	<s:form action="/signIn">
		<s:textfield label="%{getText('vc.signup.step1.username')}" name="userName" cssClass="form-input" />
		<s:password label="%{getText('vc.signup.step1.password')}" name="password" cssClass="form-input" />
		<s:textfield label="%{getText('vc.index.input_checkcode')}" name="ccode" cssClass="form-input" />
		<s:a href="javascript:void(0);" onclick="flushValidateCode();" title='<s:text name="vc.index.reload_checkcode"/>'><img id="ccode" src="signUp/captcha" border="0" /></s:a>
		<s:submit/>
	</s:form>
	
</div>

</div>


</body>
</html>